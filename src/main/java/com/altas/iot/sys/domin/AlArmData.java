package com.altas.iot.sys.domin;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
* @ClassName: AlArmData
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-26 16:56
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Data
@TableName(value = "al_arm_data")
public class AlArmData implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 报警唯一id
     */
    @TableField(value = "arm_no")
    private Long armNo;

    /**
     * 报警设备名称
     */
    @TableField(value = "arm_device_name")
    private String armDeviceName;

    /**
     * 报警信息
     */
    @TableField(value = "arm_info")
    private String armInfo;

    /**
     * 报警点位名称
     */
    @TableField(value = "arm_device_node")
    private String armDeviceNode;

    /**
     * 报警位置
     */
    @TableField(value = "arm_device_address")
    private String armDeviceAddress;

    /**
     * 报警位置唯一id
     */
    @TableField(value = "arm_device_address_no")
    private Long armDeviceAddressNo;

    /**
     * 报警设备唯一id
     */
    @TableField(value = "arm_device_no")
    private Long armDeviceNo;

    /**
     * 0：未处理 1：已处理
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 状态（0正常 1停用）
     */
    @TableField(value = "valid")
    private Boolean valid;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    // 报警设备
    @TableField(exist = false)
    private AlDevice device;
    // 报警设备视频
    @TableField(exist = false)
    private List<AlDevice> videoAlDevices;

    private static final long serialVersionUID = 1L;

    public AlArmData() {
    }

    public AlArmData(AlDevice device, String deviceOnlyNo, String armInfo) {
        this.armNo = IdWorker.getId();
        this.armDeviceName = device.getDeviceName();
        this.armInfo = armInfo;
        this.armDeviceNode = deviceOnlyNo;
        this.armDeviceAddress = device.getDeviceAddress();
        this.armDeviceAddressNo = getArmDeviceAddressNo();
        this.armDeviceNo = device.getId();
        this.createBy = "sys";
        this.createTime = new Date();
    }
}