package com.altas.iot;


import com.altas.iot.sys.utils.TimedCacheUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static com.altas.iot.sys.utils.TimedCacheUtils.TIMED_CACHE;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class IotApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
        //启动定时任务，每1秒清理一次过期条目，注释此行首次启动仍会清理过期条目
        TimedCacheUtils.run();
    }



    @Bean //必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);
        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }
}
