package com.altas.iot.sys.domin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
* @ClassName: AlSysData
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-26 15:01
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Data
public class AlSysData implements Serializable {
    private Long id;

    /**
    * 状态（0正常 1停用）
    */
    private Boolean status;

    /**
    * 字典标签
    */
    private String dictLabel;

    /**
    * 字典键值
    */
    private String dictValue;

    /**
    * 创建者
    */
    private String createBy;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 更新者
    */
    private String updateBy;

    /**
    * 更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}