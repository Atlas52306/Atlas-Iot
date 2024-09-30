package com.altas.iot.sys.service;

import com.altas.iot.sys.domin.AlSysData;

/**
 * @ClassName: AlSysDataService
 * @Description: ${description}
 * @Author: LiHanzhang
 * @Date: 2024-09-26 15:01
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/

public interface AlSysDataService {

    int deleteByPrimaryKey(Long id);

    int insert(AlSysData record);

    int insertSelective(AlSysData record);

    AlSysData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlSysData record);

    int updateByPrimaryKey(AlSysData record);

    AlSysData selectOne(AlSysData record);

    String findValue(AlSysData record);
}
