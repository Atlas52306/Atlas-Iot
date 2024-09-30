package com.altas.iot.mqtt.service;

import net.dreamlu.iot.mqtt.spring.server.MqttServerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/**
 * @version 1.0
 * @Author:LiHanZhang
 * @Date: 2022/7/27
 */
@Service
public class ServerService {

    @Autowired
    private MqttServerTemplate server;

    public boolean publish(String body) {
        server.publishAll("/test/123", ByteBuffer.wrap(body.getBytes()));
        return true;
    }
}
