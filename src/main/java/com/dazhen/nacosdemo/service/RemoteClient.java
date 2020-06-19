package com.dazhen.nacosdemo.service;

import com.dazhen.nacosdemo.config.RemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: monitor-plat
 * @description: 远程服务接口
 * @author: water
 * @create: 2020-06-18 17:08
 */
@FeignClient(name = "nacos-demo", fallback = RemoteHystrix.class)
public interface RemoteClient {

    @GetMapping("/provider")
    String provider();
}