package com.yupi.springbootinit.service;

import javax.annotation.Resource;

import cn.hutool.core.collection.ListUtil;
import com.yupi.springbootinit.model.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;

import static com.sun.javafx.font.FontResource.SALT;

/**
 * 用户服务测试
 *
 * @author <a href="https://github.com/helloWorldCwc">程序员cc</a>
 * 
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;
    private static final String SALT = "cc";
    @Test
    void userRegister() {
        String userAccount = "cctest";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        try {
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "yutest";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void nd5Test(){
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + "123").getBytes());
        System.out.println(encryptPassword);
    }
    @Test
    void userTagesTest(){
        List<String> tags = ListUtil.toList("java", "python2");
        List<UserVO> userVOList = userService.queryUserByTags(tags);
        System.out.println(userVOList);
    }
}
