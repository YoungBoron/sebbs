package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Reply;
import com.rgsj3.sebbs.domain.Topic;
import com.rgsj3.sebbs.repository.BoardRepository;
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
public class TopicController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ReplyRepository replyRepository;

    @RequestMapping("/add/topic")
    public String addTopic(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("boardId") Integer boardId,
                           HttpServletRequest httpServletRequest) {
        var userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        if (userId == null) {
            return "false";
        } else {
            var user = userRepository.findById(userId).get();
            var board = boardRepository.findById(boardId).get();
            var topic = new Topic();
            var date = new Date();
            topic.setTitle(title);
            topic.setBoard(board);
            topic.setUser(user);
            topic.setCreateDate(date);
            topic.setReplyDate(date);
            topic.setReplyUser(user);
            topic.setFloor(1);
            topic.setClick(0);
            topicRepository.save(topic);
            var reply = new Reply();
            reply.setContent(content);
            reply.setFloor(1);
            reply.setUser(user);
            reply.setTopic(topic);
            reply.setReplyDate(date);
            replyRepository.save(reply);
            return "true";
        }
    }
}
