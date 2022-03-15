package com.example.springboottimingscheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // 2.开启定时任务
public class SpringBootTimingScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTimingScheduledApplication.class, args);
    }

}
