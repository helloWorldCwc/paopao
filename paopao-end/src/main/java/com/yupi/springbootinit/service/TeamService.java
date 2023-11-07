package com.yupi.springbootinit.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.model.dto.team.TeamRequest;
import com.yupi.springbootinit.model.entity.Team;
import com.yupi.springbootinit.model.entity.User;

/**
* @author cc
* @description 针对表【team】的数据库操作Service
* @createDate 2023-11-03 16:12:38
*/
public interface TeamService extends IService<Team> {

    Long add(TeamRequest teamRequest, User user);

    Boolean quitTeam(DeleteRequest deleteRequest, User user);
}
