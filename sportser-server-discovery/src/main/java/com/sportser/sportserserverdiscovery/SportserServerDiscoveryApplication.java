package com.sportser.sportserserverdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SportserServerDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportserServerDiscoveryApplication.class, args);
    }

}
