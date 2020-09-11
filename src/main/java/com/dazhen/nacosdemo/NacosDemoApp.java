package com.dazhen.nacosdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author water
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosDemoApp {

    public static void main(String[] args) throws UnknownHostException {
        Environment env = new SpringApplication(NacosDemoApp.class).run(args).getEnvironment();
        String envPort = env.getProperty("server.port");
        String envContext = env.getProperty("server.contextPath");
        String port = envPort == null ? "8080" : envPort;
        String context = envContext == null ? "" : envContext;
        String path = port + "" + context + "/swagger-ui.html";
        log.info(
                "Access URLs:\n----------------------------------------------------------\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n----------------------------------------------------------",
                path,
                InetAddress.getLocalHost().getHostAddress(), path);
    }

}
