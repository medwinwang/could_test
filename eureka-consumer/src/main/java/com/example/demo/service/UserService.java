package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by medwin on 2018/10/9.
 */
@FeignClient(value="eureka-producer", fallback=UserService.HystrixCalculatorService.class)
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
    public String hello(@PathVariable("name") String name);

    @Component
    class HystrixCalculatorService implements UserService {

        @Override
        public String hello(String name) {
            return "test fail";
        }
    }
}
