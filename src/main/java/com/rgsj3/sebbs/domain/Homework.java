package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Homework {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "标题为空")
    private String title;
    private Date createDate;

    @NotNull(message = "截止时间为空")
    private Date deadDate;

    @OneToMany(targetEntity=File.class, mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    List<File> fileList;

    @ManyToOne(targetEntity = Course.class)
    private Course course;

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Homework() {
    }
}
