package com.sportser.sportserheartratesensordataworker.services;

import com.sportser.common.dto.HeartRateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@Slf4j
public class KafkaConsumerService {

    private final UsersService usersService;

    @Value("${spring.kafka.topic-consume}")
    private String kafkaTopicConsume;

    @Autowired
    public KafkaConsumerService(UsersService usersService) {
        this.usersService = usersService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-consume}")
    public void receiveMessage(HeartRateUserDto heartRateUserDto) throws ParseException {
        log.info("Received message from Kafka topic " + kafkaTopicConsume + ": " + heartRateUserDto);
        usersService.analyseData(heartRateUserDto);
    }
}
