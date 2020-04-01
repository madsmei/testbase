package com.abc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(basePackages = "com.abc.dao")
@SpringBootApplication
public class TestbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestbaseApplication.class, args);
    }

}
