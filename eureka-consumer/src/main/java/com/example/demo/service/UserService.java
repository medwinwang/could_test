package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by medwin on 2018/10/9.
 */
@FeignClient(value="gateway-zuul", fallback=UserService.HystrixCalculatorService.class)
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/test-zuul/hello/{name}")
    public String hello(@PathVariable("name") String name, @RequestParam("token") String token);

    @Component
    class HystrixCalculatorService implements UserService {

        @Override
        public String hello(String name, String token) {
            return "test fail";
        }
    }
}
