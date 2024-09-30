package com.altas.iot.mqtt.domain;

/**
 * @Projectname: ElectricityMeterMiddleware
 * @Filename: MQTTTopicEnum
 * @Author: LiHanzhang
 * @Data:2022/8/3 13:56
 * @Description: 迅饶网关Topic列表
 */
public enum XrMqttTopicEnum {

    INFO(0,"发布","设备上报设备信息","info"),
    STATUS(1,"发布","设备上报设备工况（ 需平台召唤）","status"),
    RTG(2,"发布","物联网关上报实时数据","rtg"),
    CALL(3,"订阅","接收对时命令","call"),
    CACK(4,"发布","响应对时命令","cack"),
    HISTORY(5,"发布","断点续传自动上报","history"),
    HISTORY_CALL(6,"订阅","接收历史数据召读命令","call"),
    HISTORY_CACK(7,"发布","响应历史数据召读命令 上传报历史数据","cack"),
    CMD_SET(8,"订阅","接收控制命令","cmd/set"),
    CMD_SET_CACK(9,"发布","响应控制命令","cmd/set/cack");

    private int key;

    private String label;

    /**
     * 备注
     */
    private String remark;

    /**
     * 接口地址
     */
    private String address;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    XrMqttTopicEnum(int key, String label, String remark, String address) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.address = address;
    }

    public static XrMqttTopicEnum getEnumByAddress(String address) {
        for (XrMqttTopicEnum topicEnum : values()) {
            if (topicEnum.getAddress().equals(address))
                return topicEnum;
        }
        return null;
    }
}
