package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.BoardRepository;
import com.rgsj3.sebbs.repository.TopicRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import com.rgsj3.sebbs.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TopicRepository topicRepository;

    /**
     * 首页
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        loginUser(model, httpServletRequest);
        var l = zoneRepository.findAll();
        model.addAttribute("zoneList", l);
        return "home";
    }

    /**
     * 板块页
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/board/{id}/{page}")
    public String board(Model model, HttpServletRequest httpServletRequest, @PathVariable("id") Integer id, @PathVariable("page") Integer page) {
        loginUser(model, httpServletRequest);
        var board = boardRepository.findById(id).get();
        model.addAttribute("board", board);
        Sort sort = new Sort(Sort.Direction.DESC, "replyDate");
        Pageable pageable = new PageRequest(page, 2, sort);
        //var topicList = topicRepository.findByBoardOrderByReplyDateDesc(board);
        var topicList = topicRepository.findByBoard(board, pageable);
        model.addAttribute("topicList", topicList);
        return "board";
    }

    @RequestMapping("/topic/{id}")
    public String topic(Model model, HttpServletRequest httpServletRequest, @PathVariable("id") Integer id) {
        loginUser(model, httpServletRequest);
        var topic = topicRepository.findById(id).get();
        topic.setClick(topic.getClick() + 1);
        topicRepository.save(topic);
        model.addAttribute("topic", topic);
        return "topic";
    }

    private void loginUser(Model model, HttpServletRequest httpServletRequest) {
        var session = httpServletRequest.getSession();
        if (session.getAttribute("userId") != null) {
            var userId = (Integer) session.getAttribute("userId");
            var user = userRepository.findById(userId).get();
            model.addAttribute("userId", userId);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("userId", null);
        }
    }
}
