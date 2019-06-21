package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.aspect.LogAnnotation;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.ReplyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ReplyController {

    @Resource
    ReplyService replyService;

    @RequestMapping("/add/reply")
    @LogAnnotation(description="回贴")
    public Result addReply(@RequestParam("content") String content,
                           @RequestParam("topicId") Integer topicId,
                           HttpServletRequest httpServletRequest) {
        return replyService.addReply(content, topicId, httpServletRequest);
    }

    @RequestMapping("/delete/reply")
    public Result deleteTopic(@RequestParam("id") Integer id,
                              HttpServletRequest httpServletRequest) {
        return replyService.deleteReply(id, httpServletRequest);
    }
}
