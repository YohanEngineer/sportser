package com.sportser.sportsernotificationchannelmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SportserNotificationChannelManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportserNotificationChannelManagerApplication.class, args);
    }

}
