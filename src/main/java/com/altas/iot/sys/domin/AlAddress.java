package com.altas.iot.sys.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
* @ClassName: AlAddress
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-29 14:53
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Data
@TableName(value = "al_address")
public class AlAddress implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 唯一id
     */
    @TableField(value = "address_no")
    private Long addressNo;

    /**
     * 位置名称
     */
    @TableField(value = "address_info")
    private String addressInfo;

    /**
     * 上级地址no
     */
    @TableField(value = "parent_no")
    private Long parentNo;

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
    private Date updateTime;

    @TableField(exist = false)
    private List<AlAddress> children;

    private static final long serialVersionUID = 1L;
}