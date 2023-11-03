package com.yupi.springbootinit.job.recommend;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.mapper.UserMapper;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.UserVO;
import com.yupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author cwc
 * @Date 2023/11/2 14:49
 * @Version 1.0
 */
@Component
@Slf4j
public class UserJob {
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedissonClient redissonClient;
//    重点用户
    private List<Long> userIds = Arrays.asList(1L);
    // 模拟缓存预热
    @Scheduled(cron = "0 19 11 * * * ")
    public void recommendUser(){
        String lockKey = String.format("paopao:user:commender:lock");
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // 不等待，锁在30s内自动过期
            boolean isLock = lock.tryLock(0L, 30L, TimeUnit.SECONDS);
            if(isLock){
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                Page<User> page = userService.page(new Page<>(1, 10), queryWrapper);
                List<UserVO> userVOList = BeanUtil.copyToList(page.getRecords(), UserVO.class);
                ValueOperations valueOperations = redisTemplate.opsForValue();
                for (Long userId : userIds) {
                    String recommendKey = String.format("paopao:user:recommend:%s", userId);
                    valueOperations.set(recommendKey, userVOList, 1, TimeUnit.DAYS);
                }
                log.info("缓存预热成功");
            }
        } catch (InterruptedException e) {
            log.error("数据预热失败啦", e);
        }finally {
            //释放锁
            if(lock != null){
                lock.unlock();
                log.info("成功释放锁");
            }
        }
    }
}
