package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "内容不能为空")
    @Lob
    private String content;
    private Date date;

    @ManyToOne(targetEntity = User.class)
    private User fromUser;

    @ManyToOne(targetEntity = User.class)
    private User toUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Message() {
    }
}
