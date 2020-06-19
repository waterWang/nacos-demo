package com.dazhen.nacosdemo.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: monitor-plat
 * @description: 从配置中心获取配置信息
 * @author: water
 * @create: 2020-06-18 17:31
 */
@Api
@RefreshScope
@RestController
public class ConfigController {

    @Value("${test.value}")
    private String config;

    @GetMapping("/getValue")
    public String getValue() {
        return config;
    }
}
