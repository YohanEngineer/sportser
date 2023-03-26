package com.sportser.sportsernotificationchannelmanager.controller;

import com.sportser.sportsernotificationchannelmanager.services.EmergencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final EmergencyService emergencyService;

    public RegistrationController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @GetMapping("/{email}")
    public String registration(@PathVariable String email){
        return emergencyService.registration(email);
    }

    @DeleteMapping("/{email}")
    public String unregister(@PathVariable String email){
        return emergencyService.unregister(email);
    }
}
