package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Message;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.MessageRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class MessageService {

    @Resource
    UserRepository userRepository;

    @Resource
    MessageRepository messageRepository;

    public void listMessage(Model model,
                          HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        user = userRepository.findById(user.getId()).get();
        var messageList = messageRepository.findByToUserOrFromUserOrderByDateDesc(user, user);
        var userList = userRepository.findAll();
        model.addAttribute("messageList", messageList);
        model.addAttribute("userList", userList);
    }

    public Result addMessage(Integer id, String content, HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return Result.error(2, "先登录");
        } else {
            var fromUser = userRepository.findById(user.getId()).get();
            var toUser = userRepository.findById(id).get();
            var date = new Date();
            var message = new Message();
            message.setContent(content);
            message.setFromUser(fromUser);
            message.setToUser(toUser);
            message.setDate(date);
            messageRepository.save(message);
            return Result.success();
        }
    }

}
