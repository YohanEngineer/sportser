package com.sportser.sportserheartratesensordatacollector.services;

import com.sportser.sportserheartratesensordatacollector.config.KafkaConfig;
import com.sportser.common.dto.HeartRateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaConfig kafkaConfig;

    @Autowired
    public KafkaProducerService(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    public void sendMessage(HeartRateUserDto heartRateUserDto) {
        kafkaConfig.kafkaTemplate().send(kafkaConfig.topicName(), heartRateUserDto);
        System.out.println("Sent message to Kafka: " + heartRateUserDto);
    }
}
