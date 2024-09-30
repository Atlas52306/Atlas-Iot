package com.altas.iot.sys.controller;

import com.altas.iot.sys.domin.AlAddress;
import com.altas.iot.sys.service.AlAddressService;
import com.altas.iot.sys.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: AlAddressController
 * @Description: 地址表
 * @Author: LiHanzhang
 * @Date: 2024-09-29 14:54
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/iot/address")
public class AlAddressController {

    @Autowired
    AlAddressService addressService;

    @RequestMapping("/getThree")
    public AjaxResult getThree() {
        AlAddress three = addressService.getThree();
        return AjaxResult.success(three);
    }
}
