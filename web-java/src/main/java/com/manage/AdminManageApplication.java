package com.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.manage.*.mapper")
public class AdminManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminManageApplication.class, args);
    }

}
