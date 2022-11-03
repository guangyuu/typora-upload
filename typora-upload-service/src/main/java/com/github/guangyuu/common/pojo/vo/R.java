package com.github.guangyuu.common.pojo.vo;

import com.github.guangyuu.common.constant.CodeEnum;

import java.io.Serializable;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 响应对象
 **/
public class R<T> implements Serializable {
    public static final long serialVersionUID = 2002121919950820L;
    /**
     * 状态 0 成功 1 失败
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public R() {
    }

    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> R<T> ok() {
        CodeEnum success = CodeEnum.SUCCESS;
        return new R<>(success.code(), success.msg(), null);
    }

    public static <T> R<T> ok(String message) {
        CodeEnum success = CodeEnum.SUCCESS;
        return new R<>(success.code(), message);
    }

    public static <T> R<T> ok(T data) {
        CodeEnum success = CodeEnum.SUCCESS;
        return new R<>(success.code(), success.msg(), data);
    }

    public static <T> R<T> ok(String message, T data) {
        CodeEnum success = CodeEnum.SUCCESS;
        return new R<>(success.code(), message, data);
    }

    public static <T> R<T> fail() {
        CodeEnum fail = CodeEnum.FAIL;
        return new R<>(fail.code(), fail.msg());
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new R<>(code, message);
    }

    public static <T> R<T> fail(String message) {
        CodeEnum fail = CodeEnum.FAIL;
        return new R<T>(fail.code(), message, null);
    }
}
