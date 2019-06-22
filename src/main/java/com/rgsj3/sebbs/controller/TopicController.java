package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.aspect.LogAnnotation;
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
    @LogAnnotation(description="发贴")
    public Result addTopic(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("boardId") Integer boardId,
                           HttpServletRequest httpServletRequest) {
        return topicService.addTopic(title, content, boardId, httpServletRequest);
    }

    @RequestMapping("/best/topic")
    public Result bestTopic(@RequestParam("id") Integer id,
                            HttpServletRequest httpServletRequest) {
        return topicService.bestTopic(id, httpServletRequest);
    }

    @RequestMapping("/up/topic")
    public Result upTopic(@RequestParam("id") Integer id,
                            HttpServletRequest httpServletRequest) {
        return topicService.upTopic(id, httpServletRequest);
    }

    @RequestMapping("/announcement/topic")
    public Result announcementTopic(@RequestParam("id") Integer id,
                          HttpServletRequest httpServletRequest) {
        return topicService.announcementTopic(id, httpServletRequest);
    }

    @RequestMapping("/delete/topic")
    public Result deleteTopic(@RequestParam("id") Integer id,
                            HttpServletRequest httpServletRequest) {
        return topicService.deleteTopic(id, httpServletRequest);
    }
}
