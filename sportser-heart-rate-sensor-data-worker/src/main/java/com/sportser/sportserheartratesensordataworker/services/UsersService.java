package com.sportser.sportserheartratesensordataworker.services;

import com.sportser.sportserheartratesensordataworker.dto.HeartRateUserDto;
import com.sportser.sportserheartratesensordataworker.dto.UserDto;
import com.sportser.sportserheartratesensordataworker.model.Users;
import com.sportser.sportserheartratesensordataworker.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;
    private final WebClient webClient;
    private final Environment environment;
    private RabbitMQProducerService rabbitMQProducerService;

    public UsersService(UsersRepository usersRepository, WebClient webClient, Environment environment, RabbitMQProducerService rabbitMQProducerService) {
        this.usersRepository = usersRepository;
        this.webClient = webClient;
        this.environment = environment;
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    public Boolean checkSubscribing(String email) {
        String ipAddress = environment.getProperty("address.user-profile");
        return webClient.get().uri("http://" + ipAddress + ":8085/user/checkSubscribing?email=" + email)
                .retrieve()
                .bodyToMono(Boolean.class).block();
    }

    public Boolean checkEmergency(Integer value, UserDto userDto) {
        // in sportive activity
        Integer limit = 222 - userDto.getAge();
        return value > limit;
    }

    public UserDto getUser(String email) {
        String ipAddress = environment.getProperty("address.user-profile");
        return webClient.get().uri("http://" + ipAddress + ":8085/user/" + email).retrieve().bodyToMono(UserDto.class).block();
    }

    public Users analyseData(HeartRateUserDto heartRateUserDto) {
        if (heartRateUserDto.getUserEmail() != null && heartRateUserDto.getUserEmail().length() > 0) {
            if (checkSubscribing(heartRateUserDto.getUserEmail())) {
                UserDto userDto = getUser(heartRateUserDto.getUserEmail());
                if (checkEmergency(heartRateUserDto.getHeartRate(), userDto)) {
                    rabbitMQProducerService.sendMessage(heartRateUserDto);
                }
            }
            return usersRepository.save(new Users(heartRateUserDto.getUserEmail(), heartRateUserDto.getHeartRate(), heartRateUserDto.getTime()));
        }
        return null;
    }

}
