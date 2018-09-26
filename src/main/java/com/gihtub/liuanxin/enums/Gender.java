package com.gihtub.liuanxin.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    Male(0), Female(1);

    int code;
    Gender(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
    @JsonCreator
    public static Gender deserializer(Object obj) {
        if (obj == null) {
            return null;
        }

        String source = obj.toString().trim();
        for (Gender em : values()) {
            if (source.equalsIgnoreCase(em.name())) {
                return em;
            }
            if (source.equalsIgnoreCase(String.valueOf(em.getCode()))) {
                return em;
            }
        }
        return null;
    }
}
