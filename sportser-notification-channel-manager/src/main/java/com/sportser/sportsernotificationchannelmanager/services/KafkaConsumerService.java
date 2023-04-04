package com.sportser.sportsernotificationchannelmanager.services;

import com.sportser.common.dto.EmergencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    private final EmergencyService emergencyService;
    private final String topic;

    public KafkaConsumerService(EmergencyService emergencyService,
                                @Value("${spring.kafka.consumer.topic}") String topic) {
        this.emergencyService = emergencyService;
        this.topic = topic;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void receiveMessage(EmergencyDto emergencyDto) {
        log.info("Received Message from Kafka topic: {}", topic);
        log.info("Received Message: {}", emergencyDto);
        emergencyService.receiveEmergency(emergencyDto);
    }

}
