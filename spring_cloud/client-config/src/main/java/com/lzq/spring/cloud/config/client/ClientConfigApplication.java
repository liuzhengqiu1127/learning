package com.lzq.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientConfigApplication.class, args);
    }
}
