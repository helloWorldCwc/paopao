package com.yupi.springbootinit.pool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author cwc
 * @Date 2023/10/27 10:06
 * @Version 1.0
 */
@SpringBootTest
public class ThreadPoolTest {
    @Test
    void testPool() throws InterruptedException {
        List<String> list = new ArrayList<>();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 1000; i++) {
            countDownLatch.countDown();
            scheduledExecutorService.execute(() -> {
                list.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName());
                count.getAndIncrement();
            });
//            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        countDownLatch.await();
        System.out.println(list.size());
    }
}
