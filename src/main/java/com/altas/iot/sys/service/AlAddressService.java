package com.altas.iot.sys.service;

import com.altas.iot.sys.domin.AlAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @ClassName: AlAddressService
* @Description: ${description}
* @Author: LiHanzhang
* @Date: 2024-09-29 14:53
* @Email: 9255520@gmail.com
* @Version: 1.0
**/

public interface AlAddressService extends IService<AlAddress>{

    AlAddress getThree();
}
