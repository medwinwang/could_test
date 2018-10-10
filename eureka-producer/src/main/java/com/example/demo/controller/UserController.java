package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by medwin on 2018/10/8.
 */

@RestController
public class UserController {
//
    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){

//        ServiceInstance instance=client.getInstances();
//        logger.info("serviceId"+instance.getServiceId()+"host:post="+instance.getHost()+":"+instance.getPort());

        System.out.println(name+" welcome . My is microservice provider user");
        return name+" welcome . My is microservice provider user";
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {

        return client.getInstances(applicationName);
    }
}
