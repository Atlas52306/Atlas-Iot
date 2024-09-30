package com.altas.iot.sys.controller;

import com.altas.iot.sys.domin.AlArmData;
import com.altas.iot.sys.domin.AlDevice;
import com.altas.iot.sys.service.AlDeviceService;
import com.altas.iot.sys.utils.AjaxResult;
import com.altas.iot.sys.utils.PageUtils;
import com.altas.iot.sys.utils.page.TableDataInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: AlDeviceController
 * @Description: 设备管理
 * @Author: LiHanzhang
 * @Date: 2024-09-29 14:37
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/iot/device")
public class AlDeviceController {

    @Autowired
    AlDeviceService deviceService;

    /**
     * 分页查询
     * @param device
     * @return
     */
    @PostMapping("/page")
    public TableDataInfo page(@RequestBody AlDevice device) {
        List<AlDevice> list = deviceService.list(new QueryWrapper<AlDevice>(device));
       return PageUtils.getDataTable(list);
    }

    @PostMapping("/list")
    public AjaxResult list(@RequestBody AlDevice device){
        List<AlDevice> list = deviceService.list(new QueryWrapper<AlDevice>(device));
        return AjaxResult.success(list);
    }


    public static void main(String[] args) {
        System.out.println(IdWorker.getId());
    }
}
