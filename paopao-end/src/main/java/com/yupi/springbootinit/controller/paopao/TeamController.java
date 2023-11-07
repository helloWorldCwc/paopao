package com.yupi.springbootinit.controller.paopao;
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
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.PostVO;
import com.yupi.springbootinit.service.TeamService;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.service.UserTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * 更新（仅管理员）
     *
     * @param postUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateTeam(@RequestBody PostUpdateRequest postUpdateRequest) {

        return ResultUtils.success(null);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<PostVO> getTeamVOById(long id, HttpServletRequest request) {

        return ResultUtils.success(null);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param postQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<PostVO>> listTeamVOByPage(@RequestBody PostQueryRequest postQueryRequest){
        return ResultUtils.success(null);
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param postQueryRequest
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<PostVO>> listMyTeamVOByPage(@RequestBody PostQueryRequest postQueryRequest) {
        return ResultUtils.success(null);
    }

    // endregion

}
