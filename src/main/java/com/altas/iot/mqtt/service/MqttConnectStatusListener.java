package com.altas.iot.mqtt.service;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.spring.server.event.MqttClientOfflineEvent;
import net.dreamlu.iot.mqtt.spring.server.event.MqttClientOnlineEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author:LiHanZhang
 * @Date: 2022/7/27
 */
@Service
@Slf4j
public class MqttConnectStatusListener {

    @EventListener
    public void online(MqttClientOnlineEvent event) {
        log.info("MqttClientOnlineEvent:{}", event);
    }

    @EventListener
    public void offline(MqttClientOfflineEvent event) {
        log.info("MqttClientOfflineEvent:{}", event);
    }

}
