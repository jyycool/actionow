package com.actionow;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Actionow 单机版后端启动类。
 */
@SpringBootApplication(scanBasePackages = "com.actionow")
@MapperScan("com.actionow.**.mapper")
@OpenAPIDefinition(info = @Info(title = "Actionow Backend API", version = "3.1.0", description = "Actionow 单机版后端 API"
))
public class ActionowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionowApplication.class, args);
    }
}
