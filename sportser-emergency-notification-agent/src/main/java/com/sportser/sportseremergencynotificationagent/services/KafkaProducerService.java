package com.sportser.sportseremergencynotificationagent.services;


import com.sportser.common.dto.EmergencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, EmergencyDto> kafkaTemplate;

    @Value("${spring.kafka.topic-produce}")
    private String topic;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, EmergencyDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EmergencyDto emergencyDto) {
        kafkaTemplate.send(topic, emergencyDto);
        log.info("Sent message to Kafka topic " + topic + ": " + emergencyDto);
    }
}
