package com.sportser.sportseremergencynotificationagent.services;

import com.sportser.sportseremergencynotificationagent.dto.EmergencyDto;
import com.sportser.sportseremergencynotificationagent.dto.HeartRateUserDto;
import com.sportser.sportseremergencynotificationagent.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@Slf4j
public class RabbitMQConsumerService {

    private final UsersRepository usersRepository;
    private RabbitMQProducerService rabbitMQProducerService;

    public RabbitMQConsumerService(UsersRepository usersRepository, RabbitMQProducerService rabbitMQProducerService) {
        this.usersRepository = usersRepository;
        this.rabbitMQProducerService = rabbitMQProducerService;
    }


    @RabbitListener(queues = "${rabbitmq.consuming-queue}")
    public void receiveMessage(HeartRateUserDto heartRateUserDto) {
        EmergencyDto emergencyDto = usersRepository.findUserAndCoach(heartRateUserDto.getUserEmail());
        emergencyDto.setTime(heartRateUserDto.getTime());
        emergencyDto.setHeartRate(heartRateUserDto.getHeartRate());

        rabbitMQProducerService.sendMessage(emergencyDto);
        log.info("Received Message from QUEUE emergency-data-collector");

    }
}
