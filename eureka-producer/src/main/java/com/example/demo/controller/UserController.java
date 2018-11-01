package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by medwin on 2018/10/8.
 */
@RefreshScope
@RestController
@Slf4j
public class UserController {
//
    @Autowired
    private DiscoveryClient client;


    @Value("${host.info}")
    private String info;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){

        return name+" welcome . My is microservice provider user：" + info;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {

        return client.getInstances(applicationName);
    }
}
