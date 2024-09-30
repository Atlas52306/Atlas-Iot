package com.altas.iot.sys.service;

import com.altas.iot.sys.domin.AlArmData;
import com.altas.iot.sys.domin.AlDevice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @ClassName: AlDeviceService
* @Description: 设备管理Service
* @Author: LiHanzhang
* @Date: 2024-09-24 16:23
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

public interface AlDeviceService extends IService<AlDevice> {

    int deleteByPrimaryKey(Long id);

    int insert(AlDevice record);

    int insertSelective(AlDevice record);

    AlDevice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlDevice record);

    int updateByPrimaryKey(AlDevice record);

//    List<AlDevice> selectList(AlDevice record);

    AlDevice findByDevice(AlDevice record);

}
