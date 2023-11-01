package com.yupi.springbootinit.mysql;

import com.yupi.springbootinit.mapper.UserMapper;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author cwc
 * @Date 2023/10/27 16:18
 * @Version 1.0
 */
@SpringBootTest
public class TestDb {
    @Autowired
    private UserService userService;
    @Test
    void add() throws InterruptedException {
//        创建线程池
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                8,
                100,
                30,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>());
        int num = 400000;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setUserName("ccccc");
            user.setUserPassword("123456");
            user.setUserAccount("dsdwcc");
            user.setUserAvatar("https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg");
            users.add(user);
        }

//        userService.saveBatch(users);
        int batchSize = 10000; // 每批次插入的数据条数

        for (int i = 0; i < num; i += batchSize) {
            final int start = i;
            final int end = Math.min(i + batchSize, num);
            // 提交任务给线程池

            threadPoolExecutor.execute(() -> {

                System.out.println(Thread.currentThread().getName());
                List<User> batch = users.subList(start, end);
                userService.saveBatch(batch);
            });
        }
//         关闭线程池
//        threadPoolExecutor.shutdown();
        // 防止子线程未执行完毕，主线程先执行完毕了。
        threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("执行完毕");
    }
}
