package com.altas.iot.mqtt.service;

import net.dreamlu.iot.mqtt.core.server.MqttServerCreator;
import net.dreamlu.iot.mqtt.spring.server.MqttServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author:LiHanZhang
 * @Date: 2022/7/27
 */
@Configuration(proxyBeanMethods = false)
public class MqttServerCustomizerConfiguration {

    @Bean
    public MqttServerCustomizer mqttServerCustomizer() {
        return new MqttServerCustomizer() {
            @Override
            public void customize(MqttServerCreator creator) {
                // 此处可自定义配置 creator，会覆盖 yml 中的配置
                System.out.println("----------------MqttServerCustomizer-----------------");
            }
        };
    }

}
