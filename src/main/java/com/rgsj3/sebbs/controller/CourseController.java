package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CourseController {
    @Resource
    CourseService courseService;

    @RequestMapping("/join/course")
    public Result join(@RequestParam("id") Integer id,
                       HttpServletRequest httpServletRequest) {
        return courseService.join(id, httpServletRequest);
    }

    @RequestMapping("/add/course")
    public Result add(@RequestParam("name") String name,
                      HttpServletRequest httpServletRequest) {
        return courseService.add(name, httpServletRequest);
    }
}
