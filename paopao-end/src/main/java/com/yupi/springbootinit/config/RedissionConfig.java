package com.yupi.springbootinit.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cwc
 * @Date 2023/11/3 10:03
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedissionConfig {
    private String port;
    private String host;

    /**
     * 创建redission客户端
     * @return
     */
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        String redisAddress = String.format("redis://%s:%s", host, port);
        config.useSingleServer().setAddress(redisAddress).setDatabase(3);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
