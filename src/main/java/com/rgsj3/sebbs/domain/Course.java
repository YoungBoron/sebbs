package com.rgsj3.sebbs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    public Course() {
    }
}
