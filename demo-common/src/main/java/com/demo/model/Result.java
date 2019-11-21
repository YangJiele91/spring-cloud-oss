package com.demo.model;

/**
 * 统一接口响应结果
 *
 * @param <T>
 */
public class Result<T> {

    // 错误码, 0表示成功, 其他失败
    private Integer code;
    // 错误信息
    private String msg;
    // 业务数据
    private T data;

    public Result() {
        this.code = 0;
        this.msg = "success";
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static Result ok() {
        return new Result();
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.setMsg(msg);
        return r;
    }

    public static <T> Result ok(T t) {
        Result<T> r = new Result<>();
        r.setData(t);
        return r;
    }

//    public static Result error() {
//        return error(500, "未知异常，请联系管理员");
//    }
//
//    public static Result error(String msg) {
//        return error(500, msg);
//    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
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
