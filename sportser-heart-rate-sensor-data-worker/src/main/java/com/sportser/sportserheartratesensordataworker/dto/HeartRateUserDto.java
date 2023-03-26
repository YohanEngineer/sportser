package com.sportser.sportserheartratesensordataworker.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@id",scope = HeartRateUserDto.class)
public class HeartRateUserDto implements Serializable {

    private String userEmail;
    private Integer heartRate;
    private Timestamp time;

}
