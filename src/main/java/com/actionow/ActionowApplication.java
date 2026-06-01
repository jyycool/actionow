package com.actionow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Actionow 单机版后端启动类。
 */
@SpringBootApplication(scanBasePackages = "com.actionow")
@MapperScan("com.actionow.**.mapper")
public class ActionowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionowApplication.class, args);
    }
}
