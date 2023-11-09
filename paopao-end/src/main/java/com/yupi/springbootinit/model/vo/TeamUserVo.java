package com.yupi.springbootinit.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author cwc
 * @Date 2023/11/8 10:02
 * @Version 1.0
 */
@Data
public class TeamUserVo implements Serializable {

    private Date jointime;
    private long userId;
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;
    /**
     * 用户标签
     */
    private String tags;

    /**
     * 创建时间
     */
    private Date createTime;

}
