package com.sportser.sportserheartratesensordataworker.services;

import com.sportser.sportserheartratesensordataworker.dto.HeartRateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@Slf4j
public class RabbitMQConsumerService {

    private final UsersService usersService;

    public RabbitMQConsumerService(UsersService usersService) {
        this.usersService = usersService;
    }


    @RabbitListener(queues = "${rabbitmq.consuming-queue}")
    public void receiveMessage(HeartRateUserDto  heartRateUserDto) throws ParseException {
        log.info("Received Message from queue hr-data-collector");
        usersService.analyseData(heartRateUserDto);
    }
}
