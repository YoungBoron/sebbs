package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.aspect.LogAnnotation;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageController {

    @Resource
    MessageService messageService;

    @RequestMapping("/add/message")
    @LogAnnotation(description="私信")
    public Result addTopic(@RequestParam("id") Integer id,
                           @RequestParam("content") String content,
                           HttpServletRequest httpServletRequest) {
        return messageService.addMessage(id, content, httpServletRequest);
    }
}
