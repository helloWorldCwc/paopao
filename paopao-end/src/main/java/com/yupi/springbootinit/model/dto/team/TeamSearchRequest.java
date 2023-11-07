package com.yupi.springbootinit.model.dto.team;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;

import java.util.Date;

/**
 * @Author cwc
 * @Date 2023/11/7 15:13
 * @Version 1.0
 */
@Data
public class TeamSearchRequest extends PageRequest {
    /**
     * 队伍名称
     */
    private String teamname;

    /**
     * 队伍最大人数
     */
    private Integer maxnum;

    /**
     * 是否公开（0公开，1私有，2加密）
     */
    private Integer status;
    /**
     * 队伍描述
     */
    private String description;

    /**
     * 队伍标签（JSON）
     */
    private String teamtags;

}
