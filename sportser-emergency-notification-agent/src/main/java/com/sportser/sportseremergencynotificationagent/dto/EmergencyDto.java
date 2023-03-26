package com.sportser.sportseremergencynotificationagent.dto;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@id",scope = EmergencyDto.class)
public class EmergencyDto implements Serializable {

    private String emailCoach;
    private String firstNameUser;
    private String lastNameUser;
    private int heartRate;
    private Timestamp time;

    public EmergencyDto(String firstNameUser, String lastNameUser, String emailCoach) {
        this.emailCoach = emailCoach;
        this.firstNameUser = firstNameUser;
        this.lastNameUser = lastNameUser;
    }
}