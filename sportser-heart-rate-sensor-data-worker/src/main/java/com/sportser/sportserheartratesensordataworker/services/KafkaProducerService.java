package com.sportser.sportserheartratesensordataworker.services;


import com.sportser.common.dto.HeartRateUserDto;
import com.sportser.sportserheartratesensordataworker.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, HeartRateUserDto> kafkaTemplate;

    private final KafkaConfig kafkaConfig;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, HeartRateUserDto> kafkaTemplate, KafkaConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    public void sendMessage(HeartRateUserDto heartRateUserDto) {
        String kafkaTopicEmergency = kafkaConfig.getKafkaTopicEmergency();
        kafkaTemplate.send(kafkaTopicEmergency, heartRateUserDto);
        log.info("Sent message to Kafka topic " + kafkaTopicEmergency + ": " + heartRateUserDto);
    }
}
