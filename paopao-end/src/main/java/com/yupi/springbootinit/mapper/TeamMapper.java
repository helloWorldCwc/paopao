package com.yupi.springbootinit.mapper;


import com.yupi.springbootinit.model.dto.team.TeamSearchRequest;
import com.yupi.springbootinit.model.dto.team.TeamUpdateRequest;
import com.yupi.springbootinit.model.entity.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.vo.TeamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author cc
* @description 针对表【team】的数据库操作Mapper
* @createDate 2023-11-03 16:12:38
* @Entity generator.domain.Team
*/
public interface TeamMapper extends BaseMapper<Team> {

    /**
     * 分页结果集
     * @param teamSearchRequest
     * @return
     */
    List<TeamVo> selectTeamUserPage(TeamSearchRequest teamSearchRequest);

    /**
     * 分页数量
     * @param teamSearchRequest
     * @return
     */
    Long selectPageCount(TeamSearchRequest teamSearchRequest);
}




