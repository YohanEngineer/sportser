package com.sportser.sportserheartratesensordataworker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Users {

    private String email;
    private Integer heartRate;
    private Timestamp time;

}
