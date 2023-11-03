package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.mapper.UserTeamMapper;
import com.yupi.springbootinit.model.entity.UserTeam;
import com.yupi.springbootinit.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author cc
* @description 针对表【user_team】的数据库操作Service实现
* @createDate 2023-11-03 16:17:07
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




