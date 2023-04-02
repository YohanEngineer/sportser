package com.sportser.sportserheartratesensordataworker.services;


import com.sportser.common.dto.HeartRateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, HeartRateUserDto> kafkaTemplate;

    @Value("${spring.kafka.topic-emergency}")
    private String kafkaTopicEmergency;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, HeartRateUserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(HeartRateUserDto heartRateUserDto) {
        kafkaTemplate.send(kafkaTopicEmergency, heartRateUserDto);
        log.info("Sent message to Kafka topic " + kafkaTopicEmergency + ": " + heartRateUserDto);
    }
}
