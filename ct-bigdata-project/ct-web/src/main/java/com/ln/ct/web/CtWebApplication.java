package com.ln.ct.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.ln.ct.web.dao")
//@ComponentScan(basePackages = { "com" })
@SpringBootApplication
public class CtWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtWebApplication.class, args);
    }

}
