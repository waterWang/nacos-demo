package com.dazhen.nacosdemo.service;


import com.dazhen.nacosdemo.common.Response;
import com.dazhen.nacosdemo.config.RemoteHystrix;
import com.dazhen.nacosdemo.dto.MailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 小海螺服务
 * @author: water
 */
@FeignClient(name = "conch", fallback = RemoteHystrix.class)
public interface ConchService {

    /**
     * 发送邮件服务
     *
     * @param mail
     * @return
     */
    @PostMapping("/conch/notice/mail")
    Response sendMail(@RequestBody MailDTO mail);
}