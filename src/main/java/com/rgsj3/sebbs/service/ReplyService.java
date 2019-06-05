package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Reply;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.ReplyRepository;
import com.rgsj3.sebbs.repository.TopicRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.TreeSet;

@Service
public class ReplyService {

    @Resource
    TopicRepository topicRepository;

    @Resource
    ReplyRepository replyRepository;

    public void listReply(Model model, Integer topicId, Integer start) {
        var topic = topicRepository.findById(topicId).get();
        topic.setClick(topic.getClick() + 1);
        topicRepository.save(topic);
        model.addAttribute("topic", topic);

        var pagesize = 10;
        Pageable pageable = PageRequest.of(start, pagesize);
        var page = replyRepository.findByTopicOrderByFloorAsc(topic, pageable);
        model.addAttribute("page", page);
        var pageNum = new TreeSet<Integer>();
        for (int i = start; i >= 0 && i > start - 3; i--) {
            pageNum.add(i);
        }
        for (int i = start; i < page.getTotalPages() && i < start + 5 && pageNum.size() <= 5; i++) {
            pageNum.add(i);
        }
        for (int i = start - 3; i >= 0 & i > start - 5 && pageNum.size() <= 5; i--) {
            pageNum.add(i);
        }
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("start", start);
    }

    public Result addReply(String content, Integer topicId, HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        var topicOptional = topicRepository.findById(topicId);
        if (user == null) {
            return Result.error(2, "先登录");
        } else if (topicOptional.isEmpty()){
            return Result.error(4, "主题错误");
        } else {
            var topic = topicOptional.get();
            var reply = new Reply();
            var date = new Date();
            reply.setUser(user);
            reply.setTopic(topic);
            reply.setReplyDate(date);
            reply.setFloor(topic.getFloor() + 1);
            reply.setContent(content);
            replyRepository.save(reply);
            topic.setFloor(topic.getFloor() + 1);
            topic.setReplyDate(date);
            topic.setReplyUser(user);
            topicRepository.save(topic);
            return Result.success();
        }
    }
}
