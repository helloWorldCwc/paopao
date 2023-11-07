package com.yupi.springbootinit.model.enums;

import lombok.Data;

/**
 * @Author cwc
 * @Date 2023/11/7 9:59
 * @Version 1.0
 */
public enum TeamStatusEnum {
    PUBLICTEAM(0, "公开"),
    PRIVATE(1, "私有"),
    encrypt(2, "加密");
   private final int status;
   private final String message;

    TeamStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
