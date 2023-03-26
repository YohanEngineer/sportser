package com.sportser.sportsernotificationchannelmanager.config;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

@Slf4j
public class MqttConfig implements MqttCallback {

    private static IMqttClient instance;

    public static IMqttClient getInstance() {
        String brokerAddressIp = "tcp://172.31.253.175:1883";
        log.info("Configuration with url : " + brokerAddressIp);
        try {
            instance = new MqttClient(brokerAddressIp,"notification-channel-manager",null);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            instance.connect(mqttConnectOptions);
            log.info("connected");
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return instance;
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("Message arrived " + s);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
