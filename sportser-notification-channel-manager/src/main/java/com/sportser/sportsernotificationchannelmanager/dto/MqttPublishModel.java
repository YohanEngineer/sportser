package com.sportser.sportsernotificationchannelmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MqttPublishModel {

    private String topic;

    private String message;

    private Boolean retained;

    private Integer qos;

}
