package com.altas.iot.mqtt.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.altas.iot.mqtt.domain.*;
import com.altas.iot.mqtt.utils.MqttUtils;
import com.altas.iot.socket.WebSocketManager;
import com.altas.iot.sys.domin.AlArmData;
import com.altas.iot.sys.domin.AlDevice;
import com.altas.iot.sys.domin.AlSysData;
import com.altas.iot.sys.service.AlArmDataService;
import com.altas.iot.sys.service.AlDeviceService;
import com.altas.iot.sys.service.AlSysDataService;
import com.altas.iot.sys.utils.TimedCacheUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.ByteBufferUtil;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import net.dreamlu.iot.mqtt.core.server.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

import java.util.List;


/**
 * @version 1.0
 * @Author:LiHanZhang
 * @Date: 2022/7/27
 */
@Service
@Slf4j
public class MqttServerMessageListener implements IMqttMessageListener {

    @Autowired
    AlDeviceService deviceService;

    @Autowired
    AlSysDataService sysDataService;

    @Autowired
    AlArmDataService armDataService;


    @Override
    public void onMessage(ChannelContext context, String clientId, Message message) {
        String s = ByteBufferUtil.toString(message.getPayload());
        log.info("接收到客户端上报消息 => clientId:{} message:{}", clientId, JSONUtil.toJsonStr(message));
        log.info("接收到网关上报消息 => payload:{}",s);


        MqMessage mqMessage = MqttUtils.createMessage(message);
        System.out.println(JSONUtil.toJsonStr(mqMessage));
        processTopic(mqMessage);
    }


    /**
     * 分Topic处理上报数据
     * @param message
     */
    public void processTopic(MqMessage message) {
        String[] split = message.getTopic().split("/");
        if (split.length == 5){
//            String address = ;
            //如果是设备数据上报
            if (split[4].equals(XrMqttTopicEnum.RTG.getAddress())){
                processRtg(message);
            }
            //todo 其他Topic待实现
        }else if (split.length == 6){//cmd/set
            //todo 待实现
        }else if (split.length == 7){//cmd/set/cack
            //todo 待实现
        }

    }

    /**
     * 处理实时数据上报
     * @param message
     */
    public void processRtg(MqMessage message) {
        //判断
        if (message.getPayload() == null && message.getPayload().getDevices().get(0) == null) {
            return;
        }
        for (MqDevices device : message.getPayload().getDevices()) {
            //查询设备信息
            String deviceName = device.getDeviceNode();
            //获取设备节点信息
            for (MqNode node : device.getNodes()) {
                //设备点位名称
                String deviceNode = deviceName+"-"+node.getLabel();
                AlDevice deviceFind = new AlDevice();
                deviceFind.setDeviceNode(deviceNode);
                deviceFind.setClientId(message.getClientId());
                //获取当前上报的设备信息
                AlDevice byDevice = deviceService.findByDevice(deviceFind);
                //设备唯一标识
                String deviceOnlyNo = message.getClientId()+"-"+deviceNode;

                if (byDevice == null){
                    log.info("设备不存在");
                    break;
                }
                if (!node.getDataQuality().equals("Good")){//如果状态为bad或者未知，则=不在线 跳出循环
                    log.info("设备ClientId为：{}，设备点名为{} 的设备不在线，当前设备状态为：{}"
                            ,message.getClientId(),deviceOnlyNo,node.getDataQuality());
                    break;
                }

                //存设备状态
                JSONObject UploadParse = JSONUtil.parseObj(byDevice);
                UploadParse.set("status", node.getValue());
                //查询设备是否满足报警条件（时间内不能报警）
                Object o = TimedCacheUtils.get(deviceOnlyNo);
                if (o != null){
                    log.info("{}设备，报警处理===============》设备规定时间内已报警，不予以处理！",deviceOnlyNo);
                    break;
                }

                //0为摄像头 忽略操作   （摄像头，摄像头不用传此参数）
                // *  1：烟感 *  2：明火感应  *  3：甲烷检测器 *  4：吸烟检测
                if (byDevice.getDeviceSonType() == 1){
                    smoke(byDevice,deviceOnlyNo,node);
                }else if (byDevice.getDeviceSonType() == 2){
                    flame(byDevice,deviceOnlyNo,node);
                }else if (byDevice.getDeviceSonType() == 3){
                    ch4(byDevice,deviceOnlyNo,node);
                } else if (byDevice.getDeviceSonType() == 4) {
                    smoking(byDevice,deviceOnlyNo,node);
                }
            }

        }
    }

    // ============逻辑处理==========
    /**
     * 处理烟感数据
     * @param device 设备信息
     * @param deviceOnlyNo 设备唯一id
     * @param node 上报数据
     */
    public void smoke(AlDevice device,String deviceOnlyNo,MqNode node){

//        //设备唯一标识
//        data.set("deviceOnlyNo",deviceOnlyNo);
//        //设备状态
//        data.set("status",node.getDataQuality());
//        //设备数据
//        String jsonStr = JSONUtil.toJsonStr(data);

        //报警值获取：somke_arm_threshold 是否报警
        AlSysData sysData = new AlSysData();
        sysData.setDictLabel("somke_arm_threshold");
        String armThreshold = sysDataService.findValue(sysData);
        if (armThreshold == null){
            return;
        }
//        Integer armValue = Integer.valueOf(node.getValue() + "");
//        Integer armThresholdValue = Integer.valueOf(armThreshold);
        //如果浓度值大于报警值，则报警
        if (Integer.valueOf(node.getValue() + "") >= Integer.valueOf(armThreshold) ){
            log.info("设备ClientId为：{}，设备点名为{} 的烟感设备报警，当前设备数据为：{}",device.getClientId(),deviceOnlyNo,node.getValue());

            String armInfo = "设备位置：”："+device.getDeviceAddress()+"，设备名为"+device.getDeviceName()+" 的烟感设备报警，浓度值为为："+node.getValue();

            AlArmData armData = new AlArmData(device,deviceOnlyNo,armInfo);

            boolean save = armDataService.save(armData);
            if (!save){
                log.error("新增报警信息失败！");
            }
            log.info("发送报警信息：" + armData);
//            JSONObject data = JSONUtil.parseObj(device);
//            data.set("armData",armData);
            AlArmData armDeviceInfos = getDeviceInfos(armData);
            //推送消息
            WebSocketManager.sentToDeviceAddressNo(device.getDeviceAddressNo()+"",JSONUtil.toJsonStr(armDeviceInfos));
            //缓存设备报警标识
            TimedCacheUtils.put(deviceOnlyNo, armData);
        }


    }
    public void smoking(AlDevice device,String deviceOnlyNo,MqNode node){
        if (Integer.valueOf(node.getValue() + "") == 0){
            log.info("设备正常,不予以报警处理");
            return;
        }
        log.info("设备ClientId为：{}，设备点名为{} 的抽烟检测设备报警，当前设备数据为：{}",device.getClientId(),deviceOnlyNo,node.getValue());

        String armInfo = "设备位置：”："+device.getDeviceAddress()+"，设备名为"+device.getDeviceName()+" 的吸烟检测设备报警";

        AlArmData armData = new AlArmData(device,deviceOnlyNo,armInfo);


        boolean save = armDataService.save(armData);
        if (!save){
            log.error("新增报警信息失败！");
        }
        log.info("发送报警信息：" + armData);

        AlArmData armDeviceInfos = getDeviceInfos(armData);
        //推送消息
        WebSocketManager.sentToDeviceAddressNo(device.getDeviceAddressNo()+"",JSONUtil.toJsonStr(armDeviceInfos));;
        //缓存设备报警标识
        TimedCacheUtils.put(deviceOnlyNo, armData);
    }
    public void flame(AlDevice device,String deviceOnlyNo,MqNode node){
        if (Integer.valueOf(node.getValue() + "") == 1){
            log.info("设备正常");
            return;
        }
        log.info("设备ClientId为：{}，设备点名为{} 的设备明火探测器报警，当前设备数据为：{}",device.getClientId(),deviceOnlyNo,node.getValue());

        String armInfo = "设备位置：”："+device.getDeviceAddress()+"，设备名为"+device.getDeviceName()+" 的明火感应设备报警";

        AlArmData armData = new AlArmData(device,deviceOnlyNo,armInfo);

        boolean save = armDataService.save(armData);
        if (!save){
            log.error("新增报警信息失败！");
        }
        log.info("发送报警信息：" + armData);
        AlArmData armDeviceInfos = getDeviceInfos(armData);
        //推送消息
        WebSocketManager.sentToDeviceAddressNo(device.getDeviceAddressNo()+"",JSONUtil.toJsonStr(armDeviceInfos));;
        //缓存设备报警标识
        TimedCacheUtils.put(deviceOnlyNo, armData);

    }

    public void ch4(AlDevice device,String deviceOnlyNo,MqNode node) {

        //报警值获取：ch4_arm_threshold 是否报警
        AlSysData sysData = new AlSysData();
        sysData.setDictLabel("ch4_arm_threshold");
        String armThreshold = sysDataService.findValue(sysData);
        if (armThreshold == null) {
            return;
        }
        //如果浓度值大于报警值，则报警
        if (Integer.valueOf(node.getValue() + "") >= Integer.valueOf(armThreshold)) {

            log.info("设备ClientId为：{}，设备点名为{} 的甲烷检测设备报警，当前设备数据为：{}", device.getClientId(), deviceOnlyNo, node.getValue());

            String armInfo = "设备位置：”：" + device.getDeviceAddress() + "，设备名为" + device.getDeviceName() + " 的甲烷探测器设备报警，浓度值为为：" + node.getValue();

            AlArmData armData = new AlArmData(device, deviceOnlyNo, armInfo);

            boolean save = armDataService.save(armData);
            if (!save) {
                log.error("新增报警信息失败！");
            }
            log.info("发送报警信息：" + armData);
            AlArmData armDeviceInfos = getDeviceInfos(armData);
            //推送消息
            WebSocketManager.sentToDeviceAddressNo(device.getDeviceAddressNo() + "", JSONUtil.toJsonStr(armDeviceInfos));
            //缓存设备报警标识
            TimedCacheUtils.put(deviceOnlyNo, armData);
        }
    }
    // ============逻辑处理==========





    public  AlArmData getDeviceInfos(AlArmData datum) {
        AlDevice device = deviceService.getById(datum.getArmDeviceNo());
        datum.setDevice(device);
        AlDevice findDevice = new AlDevice();
        findDevice.setDeviceAddressNo(device.getDeviceAddressNo());
        findDevice.setDeviceType(true);
        List<AlDevice> list = deviceService.list(new QueryWrapper<AlDevice>(findDevice));
        datum.setVideoAlDevices(list);
        return datum;
    }







}

