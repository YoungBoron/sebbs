package com.rgsj3.sebbs.domain;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String number;
    private String name;
    private String password;
    private String email;
    private String sex;
    private Integer power;
    private String type;

    @OneToMany(targetEntity=Course.class, mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courseList;

    @OneToMany(targetEntity=StudentCourse.class, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourse> studentCourseList;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<StudentCourse> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    public User() {
    }
}
