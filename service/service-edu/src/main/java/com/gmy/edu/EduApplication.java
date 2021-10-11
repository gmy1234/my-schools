package com.gmy.edu;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/1 11:52
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gmy")
public class EduApplication {

    public static void main(String[] args) {

        SpringApplication.run(EduApplication.class, args);

    }
}
