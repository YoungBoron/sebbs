package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.SystemLog;
import com.rgsj3.sebbs.repository.SystemLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class LogService {
    @Resource
    SystemLogRepository systemLogRepository;

    public void listLog(Model model, HttpServletRequest httpServletRequest) {
        var logList = systemLogRepository.findAll();
        model.addAttribute("logList", logList);
    }
}
