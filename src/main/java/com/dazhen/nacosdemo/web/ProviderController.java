package com.dazhen.nacosdemo.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nacos-demo
 * @description: 服务生产者
 * @author: water
 * @create: 2020-06-18 16:28
 */
@Api
@RestController
public class ProviderController {

    @GetMapping("/provider")
    public String provider() {
        return "你好，我是provider！";
    }

}
