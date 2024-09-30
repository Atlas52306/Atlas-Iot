package com.altas.iot.mqtt.domain.callback;

import lombok.Data;

import java.io.Serializable;

/**
 * @Projectname: ElectricityMeterMiddleware
 * @Filename: MQTTRtg
 * @Author: LiHanzhang
 * @Data:2022/8/4 15:11
 * @Description: 设备上报数据
 */
@Data
public class MQTTRtg implements Serializable {

    /**
     * 通道ID
     */
    private String clientId;

    /**
     * 对等主机（IP）
     */
    private String peerHost;

    /**
     * 设备数据
     */
    private MQTTPayload payload;

    /**
     * type
     */
    private Integer topicType;

}
