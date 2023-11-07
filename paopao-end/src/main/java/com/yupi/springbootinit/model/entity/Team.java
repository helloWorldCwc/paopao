package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName team
 */
@TableName(value ="team")
@Data
public class Team implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 创建用户
     */
    private Long userid;

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

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否删除
     */
    @TableLogic(value =  "0", delval = "1")
    private Integer isdeleted;

    /**
     * 更新时间
     *
     */
    private Date updatetime;
    private Date expiredTime;
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}