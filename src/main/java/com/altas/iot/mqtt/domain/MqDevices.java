package com.altas.iot.mqtt.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Projectname: MQTT
 * @Filename: Devices
 * @Author: LiHanzhang
 * @Data:2023/4/24 10:57
 * @Description: 设备
 */
@Data
public class MqDevices implements Serializable {

    /**
     * 设备名
     */
    private String deviceNode;

    /**
     *  节点
     */
    private List<MqNode> nodes;
}
