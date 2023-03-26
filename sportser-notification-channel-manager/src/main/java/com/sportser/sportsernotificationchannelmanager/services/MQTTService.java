package com.sportser.sportsernotificationchannelmanager.services;

import com.sportser.sportsernotificationchannelmanager.config.MqttConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MQTTService {

    private final IMqttClient mqttClient = MqttConfig.getInstance();

    public void publishMessage(String topic,String message) {
        try{
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(false);
            mqttClient.publish(topic, mqttMessage);
        }catch(org.eclipse.paho.client.mqttv3.MqttException e){
            log.info("Message publishing failed for topic "+topic);
        }
    }

    public void clear(String topic) throws MqttException {
        mqttClient.publish(topic, new byte[]{}, 0, true);
        log.info("Cleared topic " + topic);
    }
}
