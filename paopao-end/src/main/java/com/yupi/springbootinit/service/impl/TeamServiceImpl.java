package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.mapper.TeamMapper;
import com.yupi.springbootinit.model.entity.Team;
import com.yupi.springbootinit.service.TeamService;
import org.springframework.stereotype.Service;

/**
* @author cc
* @description 针对表【team】的数据库操作Service实现
* @createDate 2023-11-03 16:12:38
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService {

}




