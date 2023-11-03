package com.yupi.springbootinit.redission;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author cwc
 * @Date 2023/11/3 10:07
 * @Version 1.0
 */
@SpringBootTest
public class RedissionTest {
    @Resource
    private RedissonClient redissonClient;
    @Test
    void test1 (){
        // 操作简直不要太简单了
        String key = "paopao:redision:test";
        RList<String> list = redissonClient.getList(key);
        list.add("123456");
        String s = list.get(0);
        System.out.println(s);
        list.remove(0);
    }
}
