package com.altas.iot.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @ClassName: WebSocketConfig
 * @Description: WebSocketConfig配置类
 * @Author: LiHanzhang
 * @Date: 2024-06-18 09:38
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
