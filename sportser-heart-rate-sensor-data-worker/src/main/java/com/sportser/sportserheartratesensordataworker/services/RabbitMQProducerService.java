package com.sportser.sportserheartratesensordataworker.services;

import com.sportser.sportserheartratesensordataworker.dto.HeartRateUserDto;
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

    public void sendMessage(HeartRateUserDto heartRateUserDto) {
        amqpTemplate.convertAndSend(queue.getName(), heartRateUserDto);
        log.info("Sending to the queue " + queue.getName() + ": "+heartRateUserDto);
    }
}
