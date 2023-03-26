package com.sportser.sportseremergencynotificationagent.services;

import com.sportser.sportseremergencynotificationagent.dto.EmergencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducerService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    private Queue queue;

    public RabbitMQProducerService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(EmergencyDto emergencyDto) {
        amqpTemplate.convertAndSend(queue.getName(), emergencyDto);
        log.info("SEND another");
    }
}
