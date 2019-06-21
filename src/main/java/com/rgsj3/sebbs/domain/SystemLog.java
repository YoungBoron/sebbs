package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SystemLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Integer id;  //自增长主键
    private  String  userName;  //操作用户名
    private String 	description; //操作描述
    private  String  method; //业务方法名
    private  String  params;   //业务方法参数
    private  String  ip;  //ip地址
    private  Date time;  //操作时间
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
