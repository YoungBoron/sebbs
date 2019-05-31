package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.domain.StudentCourse;
import com.rgsj3.sebbs.domain.User;
import com.rgsj3.sebbs.repository.CourseRepository;
import com.rgsj3.sebbs.repository.StudentCourseRepository;
import com.rgsj3.sebbs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserRepository userRepository;

    @Resource
    CourseRepository courseRepository;

    @Resource
    StudentCourseRepository studentCourseRepository;

    public Result login(String name, String password, HttpServletRequest httpServletRequest) {
        var userList = userRepository.findByName(name);
        if (userList.size() == 0) {
            return Result.error(1, "用户名错误");
        } else {
            var user = userList.get(0);
            if (user.getPassword().equals(password)) {
                var session = httpServletRequest.getSession();
                session.setAttribute("user", user);
                return Result.success();
            } else {
                return Result.error(1, "密码错误");
            }
        }
    }

    public Result logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("user");
        return Result.success();
    }

    public void loginUser(Model model, HttpServletRequest httpServletRequest) {
        var session = httpServletRequest.getSession();
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("user", null);
        }
    }

    public Result modifyInfo(String userName, String userEmail, String userPassword, HttpServletRequest httpServletRequest){
        var session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        user.setEmail(userEmail);
        user.setName(userName);
        if (userPassword.length() != 0)
            user.setPassword(userPassword);
        if(userRepository.save(user) != null){
            session.setAttribute("user",user);
            System.out.println(user.toString()+" " + user.getName());
            return Result.success();
        }
        else
            return Result.error(1,"修改出错");
    }

    public void courseManagement(Model model, HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        List<Course> courseList = courseRepository.findAllByTeacherIs(user);
        for (Course c:courseList){
            Integer num = studentCourseRepository.countByCourse(c);
            c.setStuNumber(num);
            courseRepository.save(c);
        }

        model.addAttribute("courseList",courseList);

    }

}
