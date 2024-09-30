package com.altas.iot.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.altas.iot.sys.domin.AlAddress;
import com.altas.iot.sys.dao.AlAddressMapper;
import com.altas.iot.sys.service.AlAddressService;
/**
* @ClassName: AlAddressServiceImpl
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-29 14:53
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Service
public class AlAddressServiceImpl extends ServiceImpl<AlAddressMapper, AlAddress> implements AlAddressService{

    @Autowired
    AlAddressMapper addressMapper;

    @Override
    public AlAddress getThree(){
        AlAddress parentAddress = addressMapper.selectById(1);
        QueryWrapper<AlAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_no",parentAddress.getAddressNo());
        queryWrapper.eq("valid",false);
        List<AlAddress> childrenAddress = addressMapper.selectList(queryWrapper);
        parentAddress.setChildren(childrenAddress);
        return parentAddress;
    }
}
