package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Reply {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "内容不能为空")
    @Lob
    private String content;
    private Date replyDate;
    private Integer floor;

    @ManyToOne(targetEntity = Topic.class)
    private Topic topic;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Reply() {
    }

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

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
