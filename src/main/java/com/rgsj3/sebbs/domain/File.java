package com.rgsj3.sebbs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class File {
    @Id
    @GeneratedValue
    private Integer id;
    private String path;
    private String name;
    private Date date;
    private String type;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Homework.class)
    private Homework homework;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File() {
    }
}
