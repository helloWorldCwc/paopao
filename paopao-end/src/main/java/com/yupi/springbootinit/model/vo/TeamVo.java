package com.yupi.springbootinit.model.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @Author cwc
 * @Date 2023/11/7 15:09
 * @Version 1.0
 */
@Data
public class TeamVo {
    private Long id;

    private  Long userId;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String description;

    private List<TeamUserVo> users;

    private Integer isCreated;
}
