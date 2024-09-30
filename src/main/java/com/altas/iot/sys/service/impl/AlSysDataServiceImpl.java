package com.altas.iot.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.altas.iot.sys.domin.AlSysData;
import com.altas.iot.sys.dao.AlSysDataMapper;
import com.altas.iot.sys.service.AlSysDataService;
/**
* @ClassName: AlSysDataServiceImpl
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-26 15:01
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Service
public class AlSysDataServiceImpl implements AlSysDataService{

    @Autowired
    private AlSysDataMapper alSysDataMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return alSysDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AlSysData record) {
        return alSysDataMapper.insert(record);
    }

    @Override
    public int insertSelective(AlSysData record) {
        return alSysDataMapper.insertSelective(record);
    }

    @Override
    public AlSysData selectByPrimaryKey(Long id) {
        return alSysDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AlSysData record) {
        return alSysDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AlSysData record) {
        return alSysDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public AlSysData selectOne(AlSysData record){
        QueryWrapper<AlSysData> queryWrapper = new QueryWrapper<>(record);
        return alSysDataMapper.selectOne(queryWrapper);
    }

    @Override
    public String findValue(AlSysData record){
        QueryWrapper<AlSysData> queryWrapper = new QueryWrapper<>(record);
        return alSysDataMapper.selectOne(queryWrapper).getDictValue();
    }
}
