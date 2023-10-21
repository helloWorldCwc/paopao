package com.yupi.springbootinit.service;

import javax.annotation.Resource;

import cn.hutool.core.collection.ListUtil;
import com.yupi.springbootinit.model.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

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

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        try {
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "yu";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {

        }
    }
    @Test
    void userTagesTest(){
        List<String> tags = ListUtil.toList("java", "python2");
        List<UserVO> userVOList = userService.queryUserByTags(tags);
        System.out.println(userVOList);
    }
}
