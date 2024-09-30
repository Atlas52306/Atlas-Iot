package com.altas.iot.mqtt.domain.callback;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Projectname: ElectricityMeterMiddleware
 * @Filename: MQTTDevs
 * @Author: LiHanzhang
 * @Data:2022/8/4 14:14
 * @Description: 设备列表
 */
@Data
public class MQTTDevs implements Serializable {

    /**
     * 指标数据项
     */
    private List<MQTTD> d;

    /**
     * 设备名称
     */
    private String dev;

}
