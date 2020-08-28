package com.dazhen.nacosdemo.config;

import com.dazhen.nacosdemo.common.Response;
import com.dazhen.nacosdemo.dto.MailDTO;
import com.dazhen.nacosdemo.service.ConchService;
import org.springframework.stereotype.Component;

/**
 * @program: monitor-plat
 * @description: 远程熔断类
 * @author: water
 * @create: 2020-06-18 17:15
 */
@Component
public class RemoteHystrix implements ConchService {


    @Override
    public Response sendMail(MailDTO mail) {
        return Response.error("超时");
    }

}