package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.TopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TopicController {

    @Resource
    TopicService topicService;

    @RequestMapping("/add/topic")
    public Result addTopic(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("boardId") Integer boardId,
                           HttpServletRequest httpServletRequest) {
        return topicService.addTopic(title, content, boardId, httpServletRequest);
    }
}
