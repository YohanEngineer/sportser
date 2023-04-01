package com.sportser.sportserheartratesensordatacollector.controller;

        import com.sportser.sportserheartratesensordatacollector.dto.HeartRateUserDto;
        import com.sportser.sportserheartratesensordatacollector.services.KafkaProducerService;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.sql.Timestamp;

@RestController
@Slf4j
@RequestMapping("")
public class HeartRateSensorController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public HeartRateSensorController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public void addHeartRate(@RequestBody HeartRateUserDto heartRateUserDto) {
        heartRateUserDto.setTime(new Timestamp(System.currentTimeMillis()));
        kafkaProducerService.sendMessage(heartRateUserDto);
    }
}
