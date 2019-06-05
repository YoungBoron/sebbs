package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Integer stuNumber;

    public Integer getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(Integer stuNumber) {
        this.stuNumber = stuNumber;
    }

    @ManyToOne(targetEntity = User.class)
    private User teacher;

    @OneToMany(targetEntity=StudentCourse.class, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourse> studentCourseList;

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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<StudentCourse> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    public Course() {
    }
}
