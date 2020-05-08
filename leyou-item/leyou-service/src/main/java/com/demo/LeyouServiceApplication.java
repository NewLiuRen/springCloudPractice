package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.demo.mapper")
@ImportResource("classpath:transaction.xml")
public class LeyouServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouServiceApplication.class, args);
    }
}
