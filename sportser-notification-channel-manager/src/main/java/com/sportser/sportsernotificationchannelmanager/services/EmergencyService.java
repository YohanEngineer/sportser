package com.sportser.sportsernotificationchannelmanager.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sportser.sportsernotificationchannelmanager.dto.EmergencyDto;
import com.sportser.sportsernotificationchannelmanager.redis.model.Emergency;
import com.sportser.sportsernotificationchannelmanager.redis.model.Registration;
import com.sportser.sportsernotificationchannelmanager.redis.repo.EmergencyRepository;
import com.sportser.sportsernotificationchannelmanager.redis.repo.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Optional;

@Service
@Slf4j
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;
    private final RegistrationRepository registrationRepository;
    private final MQTTService mqttService;

    public EmergencyService(EmergencyRepository usersRepository, RegistrationRepository registrationRepository, MQTTService mqttService) {
        this.emergencyRepository= usersRepository;
        this.registrationRepository = registrationRepository;
        this.mqttService = mqttService;
    }

    public String registration(String email){
        Registration coachRegistration = new Registration(email,new Timestamp(System.currentTimeMillis())+"");
        //Saving the registration in cache
        registrationRepository.save(coachRegistration);
        //Checking if there are messages directed to this given coach in cache and sending them
        Optional<Emergency> coachEmergency = emergencyRepository.findById(email);
        if(coachEmergency.isPresent()){
            for(String message : coachEmergency.get().getMessages()) {
                mqttService.publishMessage(coachRegistration.getTopic(),message);
            }
            emergencyRepository.delete(coachEmergency.get());
        }
        return "Successfully registered";
    }

    public void receiveEmergency(EmergencyDto emergencyDto){
        String message = "Le client "+emergencyDto.getFirstNameUser()+" a atteint une fréquence cardiaque de "+emergencyDto.getHeartRate()+" à "+emergencyDto.getTime();
        Optional<Registration> coachRegistration = registrationRepository.findById(emergencyDto.getEmailCoach());
        // The user is logged in and his token is in cache, sending the message
        if(coachRegistration.isPresent()){
            mqttService.publishMessage(coachRegistration.get().getTopic(),message);
        }
        // The user is not logged in, saving the message in cache
        else {
            Optional<Emergency> coachEmergency = emergencyRepository.findById(emergencyDto.getEmailCoach());
            //An instance of emergency already exists for this coach
            if(coachEmergency.isPresent()){
                coachEmergency.get().getMessages().add(message);
            }
            //Creating an emergency list for this coach
            else {
                Emergency e = new Emergency(emergencyDto.getEmailCoach());
                e.getMessages().add(message);
                emergencyRepository.save(e);
            }
            log.info("Saved message to emergency cache");
        }
    }

    public String unregister(String email){
        registrationRepository.deleteById(email);
        return "Successfully unregistered";
    }

    @Scheduled(fixedRate = 10000)
    public void checkTokenTimeout() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Iterable<Registration> registrationList = registrationRepository.findAll();
        for(Registration r : registrationList){
            if(Duration.between(Timestamp.valueOf(r.getLoginTime()).toLocalDateTime(),currentTime.toLocalDateTime()).getSeconds()>600){
                //The token has been added 10minutes ago, removing it from cache
                registrationRepository.delete(r);
            }
        }
    }
}
