package com.kill.malldemo;

import com.kill.malldemo.util.IdWorker;
import com.kill.malldemo.util.SnowFlake;
import org.apache.catalina.core.ApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.kill.malldemo.Dao")
@EnableScheduling
public class MalldemoApplication {
    //加载配置文件
    //启动Tomcat
    //创建spring容器
    //自动扫描bean并将其配置到容器里
    public static void main(String[] args) {
        SpringApplication.run(MalldemoApplication.class, args);

    }

    @Bean
    public SnowFlake getSnowFlake() {
        return new SnowFlake(1L, 1L);
    }

}
