package com.esley.clients.driver;


import com.esley.clients.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "driver-service", path = "/driver", configuration = FeignClientConfiguration.class)
public interface DriverClient {
    @GetMapping("/{email}")
    Driver getSingleDriver(@PathVariable("email") String email);
}
