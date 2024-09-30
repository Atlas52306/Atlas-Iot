package com.altas.iot.mqtt.domain.callback;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Projectname: ElectricityMeterMiddleware
 * @Filename: MQTTPayload
 * @Author: LiHanzhang
 * @Data:2022/8/4 14:13
 * @Description: 设备数据
 */
@Data
public class MQTTPayload implements Serializable {

    /**
     * 设备列表
     */
    private List<MQTTDevs> devs;

    /**
     * 产品系列编号
     */
    private String pKey;

    /**
     * 网关编号
     */
    private String sn;

    /**
     * 绝对时间
     */
    private int ts;

    /**
     * 数据格式版本
     */
    private String ver;
}
