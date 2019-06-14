package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Reply;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.Topic;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.BoardRepository;
import com.rgsj3.sebbs.repository.ReplyRepository;
import com.rgsj3.sebbs.repository.TopicRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

@Service
public class TopicService {

    @Resource
    BoardRepository boardRepository;

    @Resource
    TopicRepository topicRepository;

    @Resource
    ReplyRepository replyRepository;

    public void listTopic(Model model, Integer boardId, Integer start){
        var board = boardRepository.findById(boardId).get();
        model.addAttribute("board", board);

        var pagesize = 10;

        Pageable pageable = PageRequest.of(start, pagesize);
        var page = topicRepository.findByBoardOrderByReplyDateDesc(board, pageable);
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

    public Result addTopic(String title,
                           String content,
                           Integer boardId,
                           HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        var boardOptional = boardRepository.findById(boardId);
        if (user == null) {
            return Result.error(2, "先登录");
        } else if (boardOptional.isEmpty()) {
            return Result.error(3, "板块错误");
        } else {
            var board = boardOptional.get();
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
            return Result.success();
        }
    }

    public Result bestTopic(Integer id, HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        var topicOptional = topicRepository.findById(id);
        if (user == null || !user.getType().equals("admin")) {
            return Result.error(2, "不是管理员");
        } else if (topicOptional.isEmpty()) {
            return Result.error(3, "主题错误");
        } else {
            var topic = topicOptional.get();
            topic.setBest(true);
            topicRepository.save(topic);
            return Result.success();
        }
    }

    public Result deleteTopic(Integer id, HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        var topicOptional = topicRepository.findById(id);
        if (user == null || !user.getType().equals("admin")) {
            return Result.error(2, "不是管理员");
        } else if (topicOptional.isEmpty()) {
            return Result.error(3, "主题错误");
        } else {
            var topic = topicOptional.get();
            topicRepository.delete(topic);
            return Result.success();
        }
    }
}
