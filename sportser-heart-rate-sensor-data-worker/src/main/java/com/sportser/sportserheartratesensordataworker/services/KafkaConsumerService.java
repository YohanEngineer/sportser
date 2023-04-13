package com.sportser.sportserheartratesensordataworker.services;

import com.sportser.common.dto.HeartRateUserDto;
import com.sportser.sportserheartratesensordataworker.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@Slf4j
public class KafkaConsumerService {

    private final UsersService usersService;

    private final KafkaConfig kafkaConfig;

    @Autowired
    public KafkaConsumerService(UsersService usersService, KafkaConfig kafkaConfig) {
        this.usersService = usersService;
        this.kafkaConfig = kafkaConfig;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void receiveMessage(HeartRateUserDto heartRateUserDto) throws ParseException {
        log.info("Received message from Kafka topic " + kafkaConfig.getKafkaTopicConsume() + ": " + heartRateUserDto);
        usersService.analyseData(heartRateUserDto);
    }
}
