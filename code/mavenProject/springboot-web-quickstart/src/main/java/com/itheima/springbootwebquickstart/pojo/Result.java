package com.itheima.springbootwebquickstart.pojo;

public class Result {
    private Integer code;   // 1成功，0失败
    private String msg;     // 提示信息
    private Object obj;     // 数据date


    public Result() {
    }

    public Result(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
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
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static Result success() {
        return new Result(1, "success", null);
    }
    public static Result success(String msg) {
        return new Result(1, msg, null);
    }
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    public String toString() {
        return "Result{code = " + code + ", msg = " + msg + ", obj = " + obj + "}";
    }
}
