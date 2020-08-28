package com.dazhen.nacosdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author water
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCode implements Serializable {

    private static final long serialVersionUID = 126348711987713329L;

    public static final ResponseCode SUCCESS_200 = new ResponseCode("200", "处理成功");
    public static final ResponseCode ERROR_400 = new ResponseCode("400", "请求参数错误");
    public static final ResponseCode ERROR_401 = new ResponseCode("401", "请求未提交用户认证");
    public static final ResponseCode ERROR_403 = new ResponseCode("403", "请求被拒绝执行，权限不够");
    public static final ResponseCode ERROR_404 = new ResponseCode("404", "资源未找到");
    public static final ResponseCode ERROR_408 = new ResponseCode("408", "请求超时");
    public static final ResponseCode ERROR_500 = new ResponseCode("500", "服务器错误，通常是运行时错误");
    public static final ResponseCode ERROR_501 = new ResponseCode("501", "服务器不支持当前请求的某些功能");
    public static final ResponseCode ERROR_502 = new ResponseCode("502", "错误或者无效网关");

    private String code;

    private String desc;

}
