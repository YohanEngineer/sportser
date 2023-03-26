package com.sportser.sportsernotificationchannelmanager.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash("Emergency")
public class Emergency implements Serializable {

    @Id
    private String email;
    private List<String> messages;

    public Emergency(String email) {
        this.email = email;
        this.messages = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "email='" + email + '\'' +
                ", messages=" + messages +
                '}';
    }
}