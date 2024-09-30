package com.altas.iot.mqtt.utils;

import cn.hutool.json.JSONUtil;
import com.altas.iot.mqtt.domain.MqDevices;
import com.altas.iot.mqtt.domain.MqMessage;
import com.altas.iot.mqtt.domain.MqNode;
import com.altas.iot.mqtt.domain.MqPayload;
import com.altas.iot.mqtt.domain.callback.MQTTD;
import com.altas.iot.mqtt.domain.callback.MQTTDevs;
import com.altas.iot.mqtt.domain.callback.MQTTPayload;
import net.dreamlu.iot.mqtt.codec.ByteBufferUtil;
import net.dreamlu.iot.mqtt.core.server.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MqttUtils
 * @Description: mqtt工具类
 * @Author: LiHanzhang
 * @Date: 2024-09-24 14:41
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
public  abstract class MqttUtils {
    public static MqMessage createMessage(Message message) {
        String payload = ByteBufferUtil.toString(message.getPayload());
        MQTTPayload bean = JSONUtil.toBean(payload, MQTTPayload.class);
        //信息
        MqMessage mes = new MqMessage();
        mes.setPeerHost(message.getPeerHost());
        mes.setTopic(message.getTopic());
        mes.setClientId(message.getFromClientId());
        //消息体
        MqPayload pay = new MqPayload();
        pay.setSn(bean.getSn());
        pay.setPKey(bean.getPKey());
        //设备
        List<MQTTDevs> devs = bean.getDevs();
        if (devs != null && devs.size() != 0) {
            List<MqDevices> devices = new ArrayList<>();
            for (MQTTDevs dev : devs) {
                MqDevices mqDevices = new MqDevices();
                mqDevices.setDeviceNode(dev.getDev());
                List<MqNode> nodes = new ArrayList<>();
                for (MQTTD mqttd : dev.getD()) {
                    //点名
                    MqNode mqNode = new MqNode();
                    mqNode.setLabel(mqttd.getM());
                    mqNode.setValue(mqttd.getV());
                    mqNode.setSendTime(message.getTimestamp() + "");
                    String dq = "Uncertain";
                    if (mqttd.getDq() == 64) {
                        dq = "Bad";
                    } else {
                        dq = "Good";
                    }
                    mqNode.setDataQuality(dq);
                    nodes.add(mqNode);
                }
                mqDevices.setNodes(nodes);
                devices.add(mqDevices);
            }
            //存设备
            pay.setDevices(devices);
            //存消息体
            mes.setPayload(pay);
        }
        return mes;
    }
}
