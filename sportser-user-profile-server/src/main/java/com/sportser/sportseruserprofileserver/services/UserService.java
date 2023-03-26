package com.sportser.sportseruserprofileserver.services;


import com.sportser.sportseruserprofileserver.dto.UserDto;
import com.sportser.sportseruserprofileserver.entities.UserEntity;
import com.sportser.sportseruserprofileserver.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto.getFirstName(),
                userDto.getLastName(), userDto.getEmail(),
                userDto.getHeight(), userDto.getWeight(),
                userDto.getSubscribing(), userDto.getAge());
        return userRepository.save(userEntity);
    }

    public UserDto getUser(String email){
        UserEntity userEntity = userRepository.findByEmail(email);

        return  new UserDto(userEntity.getIdUser(), userEntity.getFirstName(),
                userEntity.getLastName(), userEntity.getEmail(),
                userEntity.getHeight(), userEntity.getWeight(),
                userEntity.getSubscribing(), userEntity.getAge());
    }

    public Boolean checkSubscribing(String email){
        return userRepository.checkSubscribing(email);
    }
}
