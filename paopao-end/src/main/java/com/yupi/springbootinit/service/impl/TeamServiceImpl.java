package com.yupi.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.TeamMapper;
import com.yupi.springbootinit.mapper.UserTeamMapper;
import com.yupi.springbootinit.model.dto.team.TeamRequest;
import com.yupi.springbootinit.model.dto.team.TeamUpdateRequest;
import com.yupi.springbootinit.model.entity.Team;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.entity.UserTeam;
import com.yupi.springbootinit.model.enums.TeamStatusEnum;
import com.yupi.springbootinit.service.TeamService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author cc
* @description 针对表【team】的数据库操作Service实现
* @createDate 2023-11-03 16:12:38
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService {
    @Resource
    private UserTeamMapper userTeamMapper;
    @Resource
    private RedissonClient redissonClient;
    @Override
    @Transactional
    public Long add(TeamRequest teamRequest, User user) {
        if(user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(teamRequest.getTeamname() == null || "".equals(teamRequest.getTeamname())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍标题不能为空");
        }
        if(teamRequest.getMaxnum() == null || teamRequest.getMaxnum() < 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍最大人数不能为负");
        }
        // 过期
        if(new Date().after(teamRequest.getExpiredTime())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍过期时间错误");
        }
        if(TeamStatusEnum.PRIVATE.getStatus() == teamRequest.getStatus()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍是私有的，无法加入");
        }
        if(TeamStatusEnum.encrypt.getStatus() == teamRequest.getStatus() && (teamRequest.getPassword() == null || "".equals(teamRequest.getPassword()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "加密队伍需要设置密码");
        }
        // 还需要判断一个人创建的队伍数量
        // TODO 这儿有并发问题，大量请求后会出现多加的情况（单机可以通过悲观锁，好像暂时没有出现问题）
        RLock lock = redissonClient.getLock("team:add:lock");
        Team team = new Team();
        try {
            if(lock.tryLock(0L, 30L, TimeUnit.SECONDS)){
                QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("userId", user.getId());
                long count = count(queryWrapper);
                if(count >= 10){
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "一个人创建的队伍数量不能超过10个");
                }
                team.setUserid(user.getId());
                team.setCreatetime(new Date());
                team.setUpdatetime(new Date());
                BeanUtil.copyProperties(teamRequest, team);
                boolean save = save(team);
                // 默认情况下，队伍的创建者也是属于成员
                UserTeam userTeam = new UserTeam();
                userTeam.setUserid(user.getId());
                userTeam.setTeamid(team.getId());
                userTeam.setJointime(new Date());
                userTeam.setCreatetime(new Date());
                userTeam.setUpdatetime(new Date());
                userTeamMapper.insert(userTeam);
            }
            return team.getId();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            if(lock != null){
                lock.unlock();
            }
        }
    }

    @Override
    @Transactional
    public Boolean quitTeam(DeleteRequest deleteRequest, User user) {
        Long userId = user.getId();
        Long teamId = deleteRequest.getId();
        Team team = getById(teamId);
        if(team == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍不存在");
        }
        // 队长退伍
        if(team.getUserid().equals(userId)){
            // 如果说队伍中还有其他人，那么需要将队伍传递下去
            List<UserTeam> userTeams = userTeamMapper.selectList(Wrappers.<UserTeam>lambdaQuery()
                    .eq(UserTeam::getTeamid, teamId)
                    .orderByAsc(UserTeam::getJointime));
            if(userTeams.size() <= 1){
                remove(Wrappers.<Team>lambdaUpdate().eq(Team::getId, teamId));
                // 删除队伍表
                userTeamMapper.delete(Wrappers.<UserTeam>lambdaUpdate().eq(UserTeam::getTeamid, teamId).eq(UserTeam::getUserid, userId));
            // 还存在队友的情况, 根据退位机制
            }else {
                update(Wrappers.<Team>lambdaUpdate().eq(Team::getId, teamId).set(Team::getUserid, userTeams.get(1).getUserid()));
                userTeamMapper.delete(Wrappers.<UserTeam>lambdaUpdate().eq(UserTeam::getTeamid, teamId).eq(UserTeam::getUserid, userId));
            }
            return true;
        }
        // 队友
        userTeamMapper.delete(Wrappers.<UserTeam>lambdaUpdate().eq(UserTeam::getTeamid, teamId).eq(UserTeam::getUserid, userId));
        return true;
    }

    @Override
    public Boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User user) {
        Long id = teamUpdateRequest.getId();
        Team team = getById(id);
        if(team == null){
            throw new  BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(!team.getUserid().equals(user.getId())){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "不是队伍创建者不能修改");
        }
        Team teamCopy = BeanUtil.copyProperties(teamUpdateRequest, Team.class);
        return update(teamCopy, Wrappers.<Team>lambdaUpdate().eq(Team::getId, teamUpdateRequest.getId()));
    }
}




