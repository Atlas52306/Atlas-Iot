package com.altas.iot.sys.dao;

import com.altas.iot.sys.domin.AlDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @ClassName: AlDeviceMapper
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-24 16:23
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

@Mapper
public interface AlDeviceMapper extends BaseMapper<AlDevice> {
    int deleteByPrimaryKey(Long id);

    int insert(AlDevice record);

    int insertSelective(AlDevice record);

    AlDevice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlDevice record);

    int updateByPrimaryKey(AlDevice record);
}