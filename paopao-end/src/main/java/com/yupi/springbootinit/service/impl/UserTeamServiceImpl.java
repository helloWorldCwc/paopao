package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserTeamMapper;
import com.yupi.springbootinit.model.dto.team.JoinTeamRequest;
import com.yupi.springbootinit.model.entity.Team;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.entity.UserTeam;
import com.yupi.springbootinit.model.enums.TeamStatusEnum;
import com.yupi.springbootinit.service.TeamService;
import com.yupi.springbootinit.service.UserTeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author cc
* @description 针对表【user_team】的数据库操作Service实现
* @createDate 2023-11-03 16:17:07
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {
    @Resource
    private TeamService teamService;
    @Resource
    private UserTeamMapper userTeamMapper;

    @Override
    public Long joinTeam(JoinTeamRequest joinTeamRequest, User user) {
        // 1. 查看这个队伍是否存在
        Team team = teamService.getById(joinTeamRequest.getId());
        if(team == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍不存在");
        }
        // 2. 存在
        //  2.1 队伍是否需要密码，两者密码是否正确
        if(new Date().after(team.getExpiredTime())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "所加入的队伍已过期");
        }
        // 2.x 队伍是否需要密码
        if(TeamStatusEnum.encrypt.getStatus() == team.getStatus()){
            if(StringUtils.isBlank(joinTeamRequest.getPassword()) || StringUtils.isBlank(team.getPassword())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍所需密码不能为空");
            }
            if(!joinTeamRequest.getPassword().equals(team.getPassword())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍密码错误");
            }
        }
        List<UserTeam> list = list(Wrappers.<UserTeam>lambdaQuery().eq(UserTeam::getTeamid, joinTeamRequest.getId()));
        //  2.2 队伍人数是否已满
        if(team.getMaxnum() == null || team.getMaxnum() <= 1 || (list.size() + 1 >= team.getMaxnum() + 2)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍已经人满了");
        }
        // 2.3 不能重复入队
        if(list.stream().anyMatch(userTeam -> userTeam.getUserid().equals(user.getId()))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能重复入队");
        }
        // 构造teamuser对象
        UserTeam userTeam = new UserTeam();
        userTeam.setUserid(user.getId());
        userTeam.setTeamid(joinTeamRequest.getId());
        userTeam.setJointime(new Date());
        userTeam.setCreatetime(new Date());
        userTeam.setUpdatetime(new Date());
        // 保存
        boolean save = save(userTeam);
        return userTeam.getId();
    }


}




