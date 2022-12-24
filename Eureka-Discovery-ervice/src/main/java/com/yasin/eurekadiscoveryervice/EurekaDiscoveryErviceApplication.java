package com.yasin.eurekadiscoveryervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryErviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryErviceApplication.class, args);
    }

}
