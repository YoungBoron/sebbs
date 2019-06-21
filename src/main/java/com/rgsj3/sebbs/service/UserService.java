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
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            var user = (User)session.getAttribute("user");
            user = userRepository.findById(user.getId()).get();
            model.addAttribute("user", user);
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
            return Result.success();
        }
        else
            return Result.error(1,"修改出错");
    }

    public void courseManagement(Model model, HttpServletRequest httpServletRequest, Integer id){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        user = userRepository.findById(user.getId()).get();
        if(user.getType().equals("teacher")){

            var courseList = user.getCourseList();
            model.addAttribute("courseList", courseList);
            if (id != -1){
                Course course = courseRepository.getById(id);
                var studentCourseList = course.getStudentCourseList();
                model.addAttribute("studentCourseList", studentCourseList);
            }


        } else if (user.getType().equals("student")){
            var studentCourseList = user.getStudentCourseList();
            var courseList = new ArrayList<Course>();
            var chooseCourseList = courseRepository.findAll();
            for (StudentCourse s: studentCourseList){
                courseList.add(s.getCourse());
                chooseCourseList.remove(s.getCourse());
            }
            model.addAttribute("courseList", courseList);
            model.addAttribute("chooseCourseList", chooseCourseList);
        }

    }

    public Result setScore(Integer id, Double score){
        StudentCourse studentCourse = studentCourseRepository.getById(id);
        studentCourse.setScore(score);
        if(studentCourseRepository.save(studentCourse) != null){
            return Result.success();
        }
        else
            return Result.error(1,"修改出错");
    }

    public void listUser(Model model, HttpServletRequest httpServletRequest) {
        var userList = userRepository.findAll();
        model.addAttribute("userList", userList);
    }

    public Result updateUser(Integer id,
                             String number,
                             String name,
                             String password,
                             String type) {
        var userOptional =  userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Result.error(2, "用户不存在");
        }
        var user = userOptional.get();
        user.setNumber(number);
        user.setName(name);
        user.setPassword(password);
        user.setType(type);
        userRepository.save(user);
        return Result.success();
    }

    public Result banUser(Integer id) {
        var userOptional =  userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Result.error(2, "用户不存在");
        }
        var user = userOptional.get();
        user.setBan(!user.getBan());
        userRepository.save(user);
        return Result.success();
    }

    public Result addUser(String number,
                             String name,
                             String password,
                             String type) {
        var user = new User();
        user.setNumber(number);
        user.setName(name);
        user.setPassword(password);
        user.setType(type);
        user.setBan(false);
        userRepository.save(user);
        return Result.success();
    }
}
