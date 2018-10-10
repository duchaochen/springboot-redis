package com.adu.springboot.config;

import com.adu.springboot.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyConfig {


    @Bean
    public RedisTemplate<Object,Employee> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object,Employee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> redisSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        redisTemplate.setDefaultSerializer(redisSerializer);
        return redisTemplate;
    }
}
