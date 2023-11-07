package com.yupi.springbootinit.model.dto.team;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author cwc
 * @Date 2023/11/7 9:40
 * @Version 1.0
 */
@Data
public class TeamRequest {
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
     * 队伍描述
     */
    private String description;

    /**
     * 队伍标签（JSON）
     */
    private String teamtags;

    private Date expiredTime;
}
