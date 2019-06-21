package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.StudentCourse;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.CourseRepository;
import com.rgsj3.sebbs.repository.StudentCourseRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class CourseService {
    @Resource
    UserRepository userRepository;

    @Resource
    CourseRepository courseRepository;

    @Resource
    StudentCourseRepository studentCourseRepository;

    public Result join(Integer id, HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        var courseOptional = courseRepository.findById(id);
        if (user == null) {
            return Result.error(2, "先登录");
        } else if (courseOptional.isEmpty()) {
            return Result.error(3, "板块错误");
        } else {
            user = userRepository.findById(user.getId()).get();
            var course = courseOptional.get();
            var studentCourse = new StudentCourse();
            studentCourse.setCourse(course);
            studentCourse.setStudent(user);
            studentCourseRepository.save(studentCourse);
            return Result.success();
        }
    }

    public Result add(String name, HttpServletRequest httpServletRequest) {
        var user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return Result.error(2, "先登录");
        } else {
            var course = new Course();
            user = userRepository.findById(user.getId()).get();
            course.setName(name);
            course.setTeacher(user);
            courseRepository.save(course);
            return Result.success();
        }
    }
}
