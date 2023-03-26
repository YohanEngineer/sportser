package com.sportser.sportsernotificationchannelmanager.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Registration")
public class Registration implements Serializable {

    @Id
    private String email;
    private String topic;
    private String loginTime;

    public Registration(String email,String loginTime) {
        this.email = email;
        this.topic = email.split("@")[0];
        this.loginTime = loginTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "email='" + email + '\'' +
                ", topic='" + topic + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}