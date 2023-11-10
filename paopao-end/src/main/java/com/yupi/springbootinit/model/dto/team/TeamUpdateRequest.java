package com.yupi.springbootinit.model.dto.team;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author cwc
 * @Date 2023/11/7 15:11
 * @Version 1.0
 */
@Data
public class TeamUpdateRequest {
    private Long id;


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
     * 队伍密码（2加密情况）
     */
    private String password;

    /**
     * 队伍标签（JSON）
     */
    private String teamtags;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiredTime;
    private String description;
}
