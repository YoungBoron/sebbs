package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Reply;
import com.rgsj3.sebbs.repository.ReplyRepository;
import com.rgsj3.sebbs.repository.TopicRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ReplyController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ReplyRepository replyRepository;

    @RequestMapping("/add/reply")
    public String addReply(@RequestParam("content") String content,
                           @RequestParam("topicId") Integer topicId,
                           HttpServletRequest httpServletRequest) {
        var userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        if (userId == null) {
            return "false";
        } else {
            var user = userRepository.findById(userId).get();
            var topic = topicRepository.findById(topicId).get();
            var reply = new Reply();
            var date = new Date();
            reply.setUser(user);
            reply.setTopic(topic);;
            reply.setReplyDate(date);
            reply.setFloor(topic.getFloor() + 1);
            reply.setContent(content);
            replyRepository.save(reply);
            topic.setFloor(topic.getFloor() + 1);
            topic.setReplyDate(date);
            topic.setReplyUser(user);
            topicRepository.save(topic);
            return "true";
        }
    }
}
