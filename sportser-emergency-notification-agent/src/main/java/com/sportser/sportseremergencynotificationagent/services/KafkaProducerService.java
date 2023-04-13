package com.sportser.sportseremergencynotificationagent.services;


import com.sportser.common.dto.EmergencyDto;
import com.sportser.sportseremergencynotificationagent.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, EmergencyDto> kafkaTemplate;

   private final KafkaConfig kafkaConfig;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, EmergencyDto> kafkaTemplate, KafkaConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    public void sendMessage(EmergencyDto emergencyDto) {
        String topic = kafkaConfig.getKafkaTopicProduce();
        kafkaTemplate.send(topic, emergencyDto);
        log.info("Sent message to Kafka topic " + topic + ": " + emergencyDto);
    }
}
