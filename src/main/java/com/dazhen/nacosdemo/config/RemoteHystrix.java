package com.dazhen.nacosdemo.config;

import com.dazhen.nacosdemo.service.RemoteClient;
import org.springframework.stereotype.Component;

/**
 * @program: monitor-plat
 * @description: 远程熔断类
 * @author: water
 * @create: 2020-06-18 17:15
 */
@Component
public class RemoteHystrix implements RemoteClient {


    @Override
    public String provider() {
        return "请求超时了";
    }
}