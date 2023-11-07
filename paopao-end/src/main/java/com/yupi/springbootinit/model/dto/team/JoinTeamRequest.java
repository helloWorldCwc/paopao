package com.yupi.springbootinit.model.dto.team;

import lombok.Data;

/**
 * @Author cwc
 * @Date 2023/11/7 10:46
 * @Version 1.0
 */
@Data
public class JoinTeamRequest {
    private Long id;
    private String password;
}
