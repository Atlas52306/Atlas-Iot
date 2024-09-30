package com.altas.iot.mqtt.domain.callback;

import lombok.Data;

import java.io.Serializable;

/**
 * @Projectname: ElectricityMeterMiddleware
 * @Filename: MQTTD
 * @Author: LiHanzhang
 * @Data:2022/8/4 14:15
 * @Description: 指标数据项
 */
@Data
public class MQTTD implements Serializable {

    /**
     * 标签名称
     */
    private String m;

    /**
     * 绝对时间(秒)
     */
    private int ts;

    /**
     * 值
     */
    private Object v;

    /**
     * 数据质量
     */
    private Integer dq;

}
