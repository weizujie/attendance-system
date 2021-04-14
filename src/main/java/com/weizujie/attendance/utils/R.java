package com.weizujie.attendance.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private boolean success;

    private String message;

    private T data;

    private R(int code, boolean success, T data, String message) {
        this.code = code;
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public static <T> R<T> success(int code, boolean success, T data, String message) {
        return new R(code, success, data, message);
    }

    public static <T> R<T> success() {
        return new R(200, true, null, "操作成功");
    }

    public static <T> R<T> success(String message) {
        return success(200, true, null, message);
    }

    public static <T> R<T> fail() {
        return new R(500, false, null, "操作失败");
    }

    public static <T> R<T> fail(String message) {
        return new R(500, false, null, message);
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
