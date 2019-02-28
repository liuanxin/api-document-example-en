package com.gihtub.liuanxin.util;

/** response code */
public enum JsonCode {

    SUCCESS(200, "success. operate data or show message, depending on the specific business"),

    BAD_REQUEST(400, "param error(show msg)"),

    NOT_LOGIN(401, "need login, redirect to login page"),

    NOT_PERMISSION(403, "need permission(show msg)"),

    NOT_FOUND(404, "not found"),

    FAIL(500, "internal error, service exception, request fail(show msg)")

    // , SERVICE_FAIL(1000, "service exception")
    ;

    int flag;
    String msg;
    JsonCode(int flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public int getFlag() {
        return flag;
    }
    public String getMsg() {
        return msg;
    }
}
