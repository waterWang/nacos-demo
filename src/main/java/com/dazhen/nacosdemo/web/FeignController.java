package com.dazhen.nacosdemo.web;

import com.dazhen.nacosdemo.service.RemoteClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: monitor-plat
 * @description: 通过Feign调用远程服务
 * @author: water
 * @create: 2020-06-18 17:16
 */
@Api
@RestController
public class FeignController {

    @Autowired
    private RemoteClient remoteClient;

    @GetMapping("/feign")
    public String feign() {
        return remoteClient.provider();
    }
}
