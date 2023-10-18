package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tags
 */
@TableName(value ="tags")
@Data
public class Tags implements Serializable {
    private Long id;

    private String tagName;

    private Long parentId;

    private Integer isParent;

    private Date createTime;

    private Date updateTime;

    // 逻辑删除
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}