package com.altas.iot.mqtt.domain;

import lombok.Data;
import net.dreamlu.iot.mqtt.core.server.model.Message;

import java.io.Serializable;

/**
 * @Projectname: MQTT
 * @Filename: Message
 * @Author: LiHanzhang
 * @Data:2023/4/24 10:12
 * @Description: 信息
 */
@Data
public class MqMessage implements Serializable {

    /**
     * ip地址
     */
    private String peerHost;


    /**
     * 主题
     */
    private String topic;

    /**
     * 通道id
     */
    private String clientId;

    /**
     * 消息体
     */
    private MqPayload payload;

    public MqMessage() {
    }

    public MqMessage(String peerHost, String topic, String clientId, MqPayload payload) {
        this.peerHost = peerHost;
        this.topic = topic;
        this.clientId = clientId;
        this.payload = payload;
    }
    public MqMessage(Message message, MqPayload payload) {
        this.peerHost = message.getPeerHost();
        this.topic = message.getTopic();
        this.clientId = message.getFromClientId();
        this.payload = payload;
    }


}
