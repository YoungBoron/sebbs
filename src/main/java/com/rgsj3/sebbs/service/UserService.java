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
            return Result.success();
        }
        else
            return Result.error(1,"修改出错");
    }

    public void courseManagement(Model model, HttpServletRequest httpServletRequest, Integer id){
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        if(user.getType().equals("teacher")){

            Set<Course> courseSet = user.getCourseSet();
            model.addAttribute("courseSet",courseSet);
            if (id != -1){
                Course course = courseRepository.getById(id);
                Set<StudentCourse> studentCourseSet = course.getStudentCourses();
                model.addAttribute("studentCourseSet",studentCourseSet);
            }


        }else if (user.getType().equals("student")){
            Set<StudentCourse> studentCourseSet = user.getStudentCourses();
            Set<Course> courseSet = new HashSet<>();
            for (StudentCourse s: studentCourseSet){
                courseSet.add(s.getCourse());
            }
            model.addAttribute("courseSet",courseSet);
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

}
