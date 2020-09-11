package com.dazhen.nacosdemo;

import com.alibaba.fastjson.JSONObject;
import com.dazhen.nacosdemo.utils.RestTemplateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: monitor-plat
 * @description: rest 测试类
 * @author: water
 * @create: 2020-06-19 17:33
 */
public class RestTest {
    /**
     * 测试HTTPS请求访问博客园
     */
    @Test
    public void test() {
        CloseableHttpClient build = HttpClientBuilder.create().useSystemProperties().build();

        RestTemplate restClient = new RestTemplate(new HttpComponentsClientHttpRequestFactory(build));
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        // set form converter
        messageConverters.add(new FormHttpMessageConverter());
        restClient.setMessageConverters(messageConverters);

        String url = "http://10.1.21.81:20082/api/rest_j/v1/user/login";
        JSONObject postData = new JSONObject();
        postData.put("userName", "nanliu");
        postData.put("password", "123.Wang");
        ResponseEntity<JSONObject> json = restClient.postForEntity(url, postData, JSONObject.class);
        System.out.println("result2====================" + json.getBody().getJSONObject("data").getString("userName"));


        String url2 = "http://10.1.21.81:20048/api/rest_j/v1/entrance/execute";


        JSONObject postData2 = new JSONObject();
        postData2.put("method", "/api/rest_j/v1/entrance/execute");
        postData2.put("params", new JSONObject());
        postData2.put("executeApplicationName", "hive");
        postData2.put("executionCode", "select * from preprocess.ds_txt_final");
        postData2.put("runType", "sql");
        JSONObject json2 = restClient.postForEntity(url2, postData2, JSONObject.class).getBody();
        System.out.println("result2====================" + json2);
    }

    @Test
    public void testStr() {
        String s = "abcabcbb";
        int n = s.length(), ans = 0;
        int[] arr = new int[122];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(arr[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            arr[s.charAt(j)] = j + 1;
        }
        System.out.println(ans);

    }

    /**
     * 测试带请求头参数Headers的GET请求，POST类似
     */
    @Test
    public void testHeaders() {
        String url = "http://127.0.0.1:8080/test/Logan?age=16";
        Map<String, String> headers = new HashMap<>();
        headers.put("appId", "Basic MyAppId");
        ResponseEntity<String> entity = RestTemplateUtils.get(url, headers, String.class);
        System.out.println(entity.getStatusCode());
        System.out.println(entity.getBody());
    }

    /**
     * 测试普通表单参数的POST请求
     */
    @Test
    public void sayHello() {
        String url = "http://127.0.0.1:8080/test/sayHello";
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "Logan");
        requestBody.add("age", 12);
        ResponseEntity<JSONObject> response = RestTemplateUtils.post(url, requestBody, JSONObject.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    /**
     * 测试JSON格式请求体Body方式POST请求
     */
    @Test
    public void sayHelloBody() {
        String url = "http://127.0.0.1:8080/test/sayHelloBody";
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Logan");
        requestBody.put("age", 16);
        ResponseEntity<JSONObject> response = RestTemplateUtils.post(url, requestBody, JSONObject.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    /**
     * 测试上传文件
     */
    @Test
    public void uploadFile() {
        String url = "http://127.0.0.1:8080/test/uploadFile";
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("uploadPath", "G:\\Temp\\Test");
        requestBody.add("file", new FileSystemResource("G:\\Java\\JavaStyle.xml"));
        requestBody.add("file2", new FileSystemResource("G:\\Java\\jad.exe"));

        ResponseEntity<JSONObject> response = RestTemplateUtils.post(url, requestBody, JSONObject.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    public void downloadFile() {
        try {
            String url = "http://127.0.0.1:8080/test/downloadFile?filePath={filePath}&fileName={fileName}";

            String filePath = "G:\\Java";
            String fileName = "JavaStyle.xml";

            ResponseEntity<byte[]> response = RestTemplateUtils.get(url, byte[].class, filePath, fileName);
            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders().getContentType());

            // 如果返回时文本内容，则直接输出
            if ("text/html;charset=UTF-8".equals(response.getHeaders().getContentType().toString())) {
                System.out.println(new String(response.getBody(), "UTF-8"));
            }

            // 输出响应内容到本地文件
            else {

                File file = new File("G:\\Temp\\Test", fileName);
                if (HttpStatus.OK.equals(response.getStatusCode())) {
                    FileUtils.writeByteArrayToFile(file, response.getBody());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试下载阿里巴巴的fastjson-1.2.56.jar
     */
    @Test
    public void downloadFile2() {
        try {
            String url = "http://central.maven.org/maven2/com/alibaba/fastjson/1.2.56/fastjson-1.2.56.jar";

            ResponseEntity<byte[]> response = RestTemplateUtils.get(url, byte[].class);
            System.out.println(response.getStatusCode());

            // 下载文件路径，可根据本地磁盘位置选择下载路径
            File file = new File("G:\\Temp\\Test\\fastjson-1.2.56.jar");
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                FileUtils.writeByteArrayToFile(file, response.getBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
