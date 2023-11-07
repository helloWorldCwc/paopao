package com.yupi.springbootinit.controller.paopao;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.TeamMapper;
import com.yupi.springbootinit.model.dto.post.PostAddRequest;
import com.yupi.springbootinit.model.dto.post.PostQueryRequest;
import com.yupi.springbootinit.model.dto.post.PostUpdateRequest;
import com.yupi.springbootinit.model.dto.team.JoinTeamRequest;
import com.yupi.springbootinit.model.dto.team.TeamRequest;
import com.yupi.springbootinit.model.dto.team.TeamSearchRequest;
import com.yupi.springbootinit.model.dto.team.TeamUpdateRequest;
import com.yupi.springbootinit.model.entity.Team;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.PostVO;
import com.yupi.springbootinit.model.vo.TeamVo;
import com.yupi.springbootinit.service.TeamService;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.service.UserTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.yupi.springbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @Author cwc
 * @Date 2023/11/7 9:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {
    @Resource
    private TeamService teamService;

    @Resource
    private UserTeamService userTeamService;
    // region 增删改查

    /**
     * 创建
     *
     * @param teamRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody TeamRequest teamRequest, HttpServletRequest request) {
        if(teamRequest == null){
         throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ResultUtils.success(teamService.add(teamRequest, user));
    }

    /**
     * 加入队伍
     * @param joinTeamRequest
     * @param request
     * @return
     */

    @PostMapping("/join/team")
    public BaseResponse<Long> joinTeam(@RequestBody JoinTeamRequest joinTeamRequest, HttpServletRequest request) {
        if(joinTeamRequest == null || joinTeamRequest.getId() < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ResultUtils.success(userTeamService.joinTeam(joinTeamRequest, user));
    }
    /**
     * 退出队伍
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if(deleteRequest == null || deleteRequest.getId() < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ResultUtils.success(teamService.quitTeam(deleteRequest, user));
    }
    @GetMapping("/page/list")
    public BaseResponse<Page<TeamVo>> teamList(TeamSearchRequest teamSearchRequest){
        final LambdaQueryWrapper<Team> wrapper = Wrappers.<Team>lambdaQuery()
                .like(StringUtils.isNotBlank(teamSearchRequest.getTeamname()), Team::getTeamname, teamSearchRequest.getTeamname());
        Page<Team> page = teamService.page(new Page<Team>(teamSearchRequest.getCurrent(), teamSearchRequest.getPageSize()), wrapper);
        List<TeamVo> teamVoList = BeanUtil.copyToList(page.getRecords(), TeamVo.class);
        Page<TeamVo> teamVoPage = new Page<>();
        BeanUtil.copyProperties(page, teamVoPage);
        teamVoPage.setRecords(teamVoList);
        return ResultUtils.success(teamVoPage);
    }

    /**
     * 更新队伍信息
     *
     * @param teamUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateTeam(@RequestBody TeamUpdateRequest teamUpdateRequest) {
        return ResultUtils.success(null);
    }

    // endregion

}
