package com.esley.offense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.esley.clients")
public class OffenseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OffenseServiceApplication.class, args);
    }
}
