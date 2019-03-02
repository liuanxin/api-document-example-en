package com.gihtub.liuanxin.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** <span style="color:red;">!!!please use in Controller, and just use static method!!!</span> */
@Setter
@Getter
@NoArgsConstructor
public class JsonResult<T> {

    // It should be only the response code,
    // the current entity indicates the return after successful processing,
    // and the response code other than 200 is unified.
    // @ApiReturn("Return status")
    // private int code;

    @ApiReturn("Return message. for example: address add success. etc...")
    private String msg;

    @ApiReturn("Return data. {\"id\":1} or [{\"id\":1},{\"id\":2}] depending on the specific business")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonResult(/*int code, */String msg) {
        // this.code = code;
        this.msg = msg;
    }
    private JsonResult(/*int code, */String msg, T data) {
        // this.code = code;
        this.msg = msg;
        this.data = data;
    }


    // --------------------

    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(msg);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<T>(msg, data);
    }

    /*
    public static <T> JsonResult<T> badRequest(String msg) {
        return new JsonResult<T>(JsonCode.BAD_REQUEST.getFlag(), msg);
    }
    public static <T> JsonResult<T> notLogin(String msg) {
        return new JsonResult<T>(JsonCode.NOT_LOGIN.getFlag(), msg);
    }
    public static <T> JsonResult<T> notPermission(String msg) {
        return new JsonResult<T>(JsonCode.NOT_PERMISSION.getFlag(), msg);
    }
    public static <T> JsonResult<T> notFound() {
        return new JsonResult<T>(JsonCode.NOT_FOUND.getFlag(), "404");
    }
    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(JsonCode.FAIL.getFlag(), msg);
    }
    */

    /*
    public static <T> JsonResult<T> serviceFail(String msg) {
        return new JsonResult<T>(JsonCode.SERVICE_FAIL.getFlag(), msg);
    }
    */
}
