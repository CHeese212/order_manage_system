package com.cheese.order_manage_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 *
 * @author July
 */
@SpringBootApplication
@MapperScan("com.cheese.order_manage_system.mapper")
public class OrderManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManageSystemApplication.class, args);
    }
}
