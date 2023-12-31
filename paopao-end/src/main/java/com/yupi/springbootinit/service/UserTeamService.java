package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.model.dto.team.JoinTeamRequest;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.entity.UserTeam;

/**
* @author cc
* @description 针对表【user_team】的数据库操作Service
* @createDate 2023-11-03 16:17:07
*/
public interface UserTeamService extends IService<UserTeam> {

    Long joinTeam(JoinTeamRequest joinTeamRequest, User user);
}
