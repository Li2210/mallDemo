package com.kill.malldemo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description TODO
 * @Author lishen
 * @Date 28/7/21 9:27 pm
 **/
@Configuration
public class RedisConfig {

    @Bean("myRedisTemplate")
    public RedisTemplate<String, String> myRedisTemplate(RedisConnectionFactory factory) throws Exception {
        RedisTemplate<String,String> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setConnectionFactory(factory);
        return template;
    }

}
