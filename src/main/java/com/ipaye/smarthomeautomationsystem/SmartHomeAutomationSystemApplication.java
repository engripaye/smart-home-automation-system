package com.ipaye.smarthomeautomationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmartHomeAutomationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeAutomationSystemApplication.class, args);
    }

}
