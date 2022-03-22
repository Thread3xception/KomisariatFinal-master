package com.esley.ticketoffense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.esley.clients")
public class TicketOffenseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketOffenseServiceApplication.class, args);
    }
}
