package com.altas.iot.sys.domin;

import lombok.Data;

/**
* @ClassName: AlDevice
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-24 16:23
* @Email: 9255520@gmail.com
* @Version: 1.0
**/
@Data
public class AlDevice {
    /**
     * 主键
     */
    private Long id;

    /**
     * 设备唯一地址id
     */
    private Long deviceAddressNo;

    /**
     * 设备名
     */
    private String deviceName;

    /**
     * 设备点位名称dev-m
     */
    private String deviceNode;

    /**
     * 设备位置
     */
    private String deviceAddress;

    private String clientId;

    /**
     * 设备类型 0：设备 1：网关
     */
    private Boolean deviceType;

    /**
     * 视频流url
     */
    private String videoUrl;
    /**
     * 设备子类型
     *  0：默认（摄像头，摄像头不用传此参数）
     *  1：烟感
     *  2：明火感应
     *  3：甲烷检测器
     *  4：吸烟检测
     */
    private Integer deviceSonType;
}