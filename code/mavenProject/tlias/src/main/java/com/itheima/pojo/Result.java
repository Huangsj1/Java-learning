package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;   // 响应码：1表示成功，0表示失败
    private String msg;     // 响应信息：描述字符串
    private Object data;    // 返回的数据

    // 成功响应：增删改
    public static Result success() {
        return new Result(1, "success", null);
    }

    // 成功响应：查
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    // 失败响应
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }
}
