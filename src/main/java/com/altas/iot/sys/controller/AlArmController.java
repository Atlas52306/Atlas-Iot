package com.altas.iot.sys.controller;

import com.altas.iot.sys.domin.AlArmData;
import com.altas.iot.sys.domin.AlDevice;
import com.altas.iot.sys.service.AlArmDataService;
import com.altas.iot.sys.service.AlDeviceService;
import com.altas.iot.sys.utils.AjaxResult;
import com.altas.iot.sys.utils.HttpStatus;
import com.altas.iot.sys.utils.PageUtils;
import com.altas.iot.sys.utils.page.TableDataInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: AlArmController
 * @Description: 报警信息controller层
 * @Author: LiHanzhang
 * @Date: 2024-09-27 16:38
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/iot/arm")
public class AlArmController {

    @Autowired
    AlArmDataService armDataService;

    @Autowired
    AlDeviceService deviceService;

    /**
     * 分页查询报警信息
     * @param armData
     * @return
     */
    @PostMapping("/page")
    public TableDataInfo page(@RequestBody AlArmData armData) {
        PageUtils.startPage();
        List<AlArmData> data = armDataService.list(new QueryWrapper<AlArmData>(armData));
        for (AlArmData datum : data) {
            AlDevice device = deviceService.getById(datum.getArmDeviceNo());
            datum.setDevice(device);
            AlDevice findDevice = new AlDevice();
            findDevice.setDeviceAddressNo(datum.getArmDeviceAddressNo());
            findDevice.setDeviceType(true);
            List<AlDevice> list = deviceService.list(new QueryWrapper<AlDevice>(findDevice));
            datum.setVideoAlDevices(list);;
        }
        return PageUtils.getDataTable(data);

    }



    @PostMapping("/updateAlarmInfo")
    public AjaxResult updateAlarmInfo(@RequestBody AlArmData armData){
        AlArmData data = new AlArmData();
        data.setId(armData.getId());
        data.setStatus(armData.getStatus());
        data.setUpdateTime(new Date());
        boolean b = armDataService.updateById(data);
        if (!b){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }







}
