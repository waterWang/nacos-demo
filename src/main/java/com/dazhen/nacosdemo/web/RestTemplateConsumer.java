package com.dazhen.nacosdemo.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: nacos-demo
 * @description: RestTemplate 模式的消费者
 * @author: water
 * @create: 2020-06-18 16:57
 */
@Api
@RestController
public class RestTemplateConsumer {

    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("/consumer")
    public String consumer() {
        String result = restTemplate.getForObject("http://nacos-demo/provider", String.class);
        return "Return : " + result;
    }
}
