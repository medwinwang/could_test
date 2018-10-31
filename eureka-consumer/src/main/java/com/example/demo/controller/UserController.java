package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by medwin on 2018/10/9.
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/tohello")
    public  String tohello(String name, String token){
        return userService.hello(name, token);
    }
}
