package com.dazhen.nacosdemo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author water
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    @ApiModelProperty(value = "收件人(如果多个收件人中间英文逗号间隔)", required = true)
    private String to;

    @ApiModelProperty(value = "邮件主题", required = true)
    private String subject;

    @ApiModelProperty(value = "邮件内容", required = true)
    private String text;
}
