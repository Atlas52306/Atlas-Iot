package com.altas.iot.sys.service.impl;

import com.altas.iot.sys.dao.AlArmDataMapper;
import com.altas.iot.sys.domin.AlArmData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.altas.iot.sys.dao.AlDeviceMapper;
import com.altas.iot.sys.domin.AlDevice;
import com.altas.iot.sys.service.AlDeviceService;

/**
* @ClassName: AlDeviceServiceImpl
* @Description: 设备管理ServiceImpl类
* @Author: LiHanzhang
* @Date: 2024-09-24 16:23
* @Email: 9255520@gmail.com
* @Version: 1.0
**/
@Slf4j
@Service
public class AlDeviceServiceImpl extends ServiceImpl<AlDeviceMapper, AlDevice> implements AlDeviceService {

    @Autowired
    public AlDeviceMapper alDeviceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return alDeviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AlDevice record) {
        return alDeviceMapper.insert(record);
    }

    @Override
    public int insertSelective(AlDevice record) {
        return alDeviceMapper.insertSelective(record);
    }

    @Override
    public AlDevice selectByPrimaryKey(Long id) {
        return alDeviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AlDevice record) {
        return alDeviceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AlDevice record) {
        return alDeviceMapper.updateByPrimaryKey(record);
    }

    @Override
    public AlDevice findByDevice(AlDevice record) {
        QueryWrapper<AlDevice> query = new QueryWrapper<>(record);
        return alDeviceMapper.selectOne(query);
    }

}
