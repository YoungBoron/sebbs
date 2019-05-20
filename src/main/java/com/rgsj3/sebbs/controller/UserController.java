package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Reply;
import com.rgsj3.sebbs.repository.ReplyRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * 登录
     * @param name
     * @param password
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpServletRequest httpServletRequest) {
        var userList = userRepository.findByName(name);
        if (userList.size() == 0) {
            return "false";
        } else {
            var user = userList.get(0);
            if (user.getPassword().equals(password)) {
                var session = httpServletRequest.getSession();
                session.setAttribute("userId", user.getId());
                return "true";
            } else {
                return "false";
            }
        }
    }

    /**
     * 登出
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("userId");
        return "true";
    }

    /**
     * 是否登录
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/isLogin")
    public String isLogin(HttpServletRequest httpServletRequest) {
        var session = httpServletRequest.getSession();
        if (session.getAttribute("userId") != null) {
            return session.getAttribute("userId").toString();
        } else {
            return "false";
        }
    }

}
