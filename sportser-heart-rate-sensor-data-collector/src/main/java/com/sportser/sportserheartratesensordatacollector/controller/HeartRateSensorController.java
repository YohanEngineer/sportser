package com.sportser.sportserheartratesensordatacollector.controller;

import com.sportser.sportserheartratesensordatacollector.dto.HeartRateUserDto;
import com.sportser.sportserheartratesensordatacollector.services.RabbitMQProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("")
public class HeartRateSensorController {

    private final RabbitMQProducerService rabbitMQProducerService;

    public HeartRateSensorController(RabbitMQProducerService rabbitMQProducerService) {
        this.rabbitMQProducerService = rabbitMQProducerService;
    }


    @PostMapping
    public void addHeartRate(@RequestBody HeartRateUserDto heartRateUserDto) {
        heartRateUserDto.setTime(new Timestamp(System.currentTimeMillis()));
        rabbitMQProducerService.sendMessage(heartRateUserDto);
    }
}

