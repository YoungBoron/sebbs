package com.rgsj3.sebbs.domain;

public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static Result success(Object object) {
        return new Result<Object>(0, "success", object);
    }

    public static Result success() {
        return new Result<Object>(0, "success", null);
    }

    public static Result error(Integer code, String msg) {
        return new Result<Object>(code, msg, null);
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
