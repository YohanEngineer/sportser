package com.sportser.sportserheartratesensordataworker.controller;

import com.sportser.common.dto.HeartRateUserDto;
import com.sportser.sportserheartratesensordataworker.model.Users;
import com.sportser.sportserheartratesensordataworker.services.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("")
    public Users analyseData(@RequestBody HeartRateUserDto heartRateUserDto){
        return usersService.analyseData(heartRateUserDto);
    }

}
