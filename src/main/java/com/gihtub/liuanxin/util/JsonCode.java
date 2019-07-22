package com.gihtub.liuanxin.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** response code */
public enum JsonCode {

    // The return code is used on the http response code. It needs to be added when the front end needs to change the page logic.
    // For example, the following 400 403 404 is the output msg for the front end, so can use 500 to return

    /** operate data or show message, depending on the specific business */
    SUCCESS(200, "success"),

    // BAD_REQUEST(400, "param error"),

    /** redirect to login page */
    NOT_LOGIN(401, "need login"),

    // NOT_PERMISSION(403, "need permission"),

    // NOT_FOUND(404, "not found"),

    FAIL(500, "internal error or service exception")

    // , SERVICE_FAIL(1000, "service exception")
    ;

    int code;
    String value;
    JsonCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }
    public String getValue() {
        return value;
    }


    @JsonValue
    public int serializer() {
        return code;
    }
    @JsonCreator
    public static JsonCode deserializer(Object obj) {
        if (obj != null) {
            String des = obj.toString();
            for (JsonCode jsonCode : values()) {
                if (des.equals(String.valueOf(jsonCode.code))) {
                    return jsonCode;
                }
                if (des.equalsIgnoreCase(jsonCode.name())) {
                    return jsonCode;
                }
            }
        }
        return SUCCESS;
    }
}
