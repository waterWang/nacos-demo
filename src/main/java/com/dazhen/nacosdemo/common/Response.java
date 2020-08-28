package com.dazhen.nacosdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author water
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1222614520893986846L;

    private T data;
    private String code;
    private String msg;


    @Override
    public String toString() {
        return "Response [data:" + data + ", code:" + code + ", msg:" + msg + "]";
    }

    public static Response<String> success(String msg) {
        return new Response<>(null, "200", msg);
    }

    public static <T> Response<T> success(T data) {
        return Response.create(data, ResponseCode.SUCCESS_200);
    }

    public static <T> Response<T> create(T data, ResponseCode rc) {
        return new Response<T>(data, rc.getCode(), rc.getDesc());
    }

    public static Response<String> success() {
        return success("success");
    }

    public static Response<?> out(Object data) {
        return new Response<>(data, "200", "success");
    }

    public static Response<?> error(String msg) {
        return error(msg, "500");
    }

    public static Response<?> notFound(String msg) {
        return error(msg, "401");
    }

    public static Response<?> error(String msg, String errCode) {
        return new Response<>(null, errCode, msg);
    }

    public static Response<?> except(String msg, String exception) {
        return new Response<>(exception, "500", msg);
    }

    public static Response<?> except(Object ex, String msg) {
        return new Response<>(ex, "500", msg);
    }

    public static Response<?> except(String msg) {
        return new Response<>(null, "500", msg);
    }

    public static Response<?> error(List<String> errMsgs, String errCode) {
        return new Response<>(errMsgs, errCode, "Authorization error");
    }

}
