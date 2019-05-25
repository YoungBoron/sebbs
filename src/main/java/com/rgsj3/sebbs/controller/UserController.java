package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpServletRequest httpServletRequest) {
        return userService.login(name, password, httpServletRequest);
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest httpServletRequest) {
        return userService.logout(httpServletRequest);
    }

}
