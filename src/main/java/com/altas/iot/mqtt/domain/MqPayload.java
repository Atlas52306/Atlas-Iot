package com.altas.iot.mqtt.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Projectname: MQTT
 * @Filename: Payload
 * @Author: LiHanzhang
 * @Data:2023/4/24 10:46
 * @Description: 消息体
 */
@Data
public class MqPayload implements Serializable {


    private String pKey;

    /**
     * 设备序列号
     */
    private String sn;

    /**
     * 设备
     */
    List<MqDevices> devices;


}
