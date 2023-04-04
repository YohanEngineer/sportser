package com.sportser.sportseremergencynotificationagent.services;

import com.sportser.common.dto.HeartRateUserDto;
import com.sportser.common.dto.EmergencyDto;
import com.sportser.sportseremergencynotificationagent.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    private final UsersRepository usersRepository;
    private KafkaProducerService kafkaProducerService;

    public KafkaConsumerService(UsersRepository usersRepository, KafkaProducerService kafkaProducerService) {
        this.usersRepository = usersRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-consume}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(HeartRateUserDto heartRateUserDto) {
        EmergencyDto emergencyDto = usersRepository.findUserAndCoach(heartRateUserDto.getUserEmail());
        emergencyDto.setTime(heartRateUserDto.getTime());
        emergencyDto.setHeartRate(heartRateUserDto.getHeartRate());

        kafkaProducerService.sendMessage(emergencyDto);
        log.info("Received Message from TOPIC emergency-data-collector");
    }
}
