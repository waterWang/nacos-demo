package com.dazhen.nacosdemo.web;

import com.dazhen.nacosdemo.common.Response;
import com.dazhen.nacosdemo.dto.MailDTO;
import com.dazhen.nacosdemo.service.ConchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ConchService conchService;


    @ApiOperation("邮件通知API ")
    @PostMapping(value = "/notice/mail")
    public Response mail(@RequestBody MailDTO dto) {
        return conchService.sendMail(dto);
    }


}
