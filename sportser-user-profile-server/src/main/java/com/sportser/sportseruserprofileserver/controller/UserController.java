package com.sportser.sportseruserprofileserver.controller;

import com.sportser.sportseruserprofileserver.dto.UserDto;
import com.sportser.sportseruserprofileserver.entities.UserEntity;
import com.sportser.sportseruserprofileserver.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUser(email));
    }

    @GetMapping("/checkSubscribing")
    public ResponseEntity<Boolean> checkSubscribing(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.checkSubscribing(email));
    }
}
