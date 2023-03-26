package com.sportser.sportsernotificationchannelmanager.services;

import com.sportser.sportsernotificationchannelmanager.dto.EmergencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumerService {

    private final EmergencyService emergencyService;

    public RabbitMQConsumerService(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }


    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(EmergencyDto emergencyDto) {
        log.info("Received Message from queue notification-channel");
        emergencyService.receiveEmergency(emergencyDto);
    }
}
