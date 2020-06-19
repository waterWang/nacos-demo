package com.dazhen.nacosdemo.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @program: monitor-plat
 * @description: RestTemplate工具类
 * @author: water
 * @create: 2020-06-18 22:00
 */
@Slf4j
public class RestTemplateUtils {


    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplate();
    }

    private RestTemplateUtils() {
    }


    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    /**
     * post 请求
     *
     * @param url   请求路径
     * @param data  body数据
     * @param token JWT所需的Token，不需要的可去掉
     * @return
     */
    public static String post(String url, String data, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        return RestTemplateUtils.getInstance().postForObject(url, requestEntity, String.class);
    }

    /**
     * get 请求
     *
     * @param url   请求路径
     * @param token JWT所需的Token，不需要的可去掉
     * @return
     */
    public static String get(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = RestTemplateUtils.getInstance().exchange(url, HttpMethod.GET, requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }


}