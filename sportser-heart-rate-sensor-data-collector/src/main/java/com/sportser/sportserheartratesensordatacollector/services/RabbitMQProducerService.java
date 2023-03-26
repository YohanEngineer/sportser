package com.sportser.sportserheartratesensordatacollector.services;

import com.sportser.sportserheartratesensordatacollector.dto.HeartRateUserDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    private Queue queue;

    public RabbitMQProducerService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(HeartRateUserDto heartRateUserDto) {
        amqpTemplate.convertAndSend(queue.getName(), heartRateUserDto);
        System.out.println(queue.getName() + heartRateUserDto);
    }
}
