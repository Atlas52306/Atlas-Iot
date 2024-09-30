package com.altas.iot.mqtt.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * @Projectname: MQTT
 * @Filename: Node
 * @Author: LiHanzhang
 * @Data:2023/4/24 11:08
 * @Description: 点位信息
 */
@Data
public class MqNode implements Serializable {

    /**
     * 标签名称
     */
    private String label;

    /**
     * 值
     */
    private Object value;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String sendTime;

    /**
     * 数据质量
     */
    private String dataQuality;
}
