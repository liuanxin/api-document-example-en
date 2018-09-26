package com.gihtub.liuanxin.util;

/** response code */
public enum JsonCode {

    SUCCESS(200, "success. operate data or show message, depending on the specific business"),

    // by frond-end, this same with 500
    // BAD_REQUEST(400, "param error(show msg)"),

    /** by frond-end, guided to login page */
    NOT_LOGIN(401, "need login(guided to login page)"),

    // by frond-end, this same with 500
    // NOT_PERMISSION(403, "need permission(show msg)"),

    NOT_FOUND(404, "not found"),

    /** by frond-end, show msg with user */
    FAIL(500, "param error, need permission, internal error, request fail(show msg)");

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
