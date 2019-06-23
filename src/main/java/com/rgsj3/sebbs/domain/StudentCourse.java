package com.rgsj3.sebbs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class StudentCourse {
    @Id
    @GeneratedValue
    private Integer id;

    @Max(value = 100, message = "分数必须小于等于100")
    @Min(value = 0, message = "分数必须大于等于0")
    private Double score;

    @ManyToOne(targetEntity = Course.class)
    private Course course;

    @ManyToOne(targetEntity = User.class)
    private User student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", score=" + score +
                ", course=" + course +
                ", student=" + student +
                '}';
    }

    public StudentCourse() {
    }
}
