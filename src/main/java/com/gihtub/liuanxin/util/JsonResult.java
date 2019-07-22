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

    // @ApiReturn("Return status")
    // private JsonCode code;

    @ApiReturn("Return message. for example: address add success. etc...")
    private String msg;

    @ApiReturn("Return data. {\"id\":1} or [{\"id\":1},{\"id\":2}] depending on the specific business")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonResult(JsonCode code, String msg) {
        // this.code = code;
        this.msg = msg;
    }
    private JsonResult(JsonCode code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }


    // ---------- In the controller, only use the following static method. Do not new JsonResult()... ----------

    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(JsonCode.SUCCESS, msg);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<T>(JsonCode.SUCCESS, msg, data);
    }


    public static <T> JsonResult<T> badRequest(String msg) {
        // return new JsonResult<T>(JsonCode.BAD_REQUEST, msg);
        return new JsonResult<T>(JsonCode.FAIL, msg);
    }
    public static <T> JsonResult<T> notLogin(String msg) {
        return new JsonResult<T>(JsonCode.NOT_LOGIN, msg);
    }
    public static <T> JsonResult<T> notPermission(String msg) {
        // return new JsonResult<T>(JsonCode.NOT_PERMISSION, msg);
        return new JsonResult<T>(JsonCode.FAIL, msg);
    }
    public static <T> JsonResult<T> notFound() {
        // return new JsonResult<T>(JsonCode.NOT_FOUND, "404");
        return new JsonResult<T>(JsonCode.FAIL, "404");
    }
    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(JsonCode.FAIL, msg);
    }

    // public static <T> JsonResult<T> serviceFail(String msg) {
    //     return new JsonResult<T>(JsonCode.SERVICE_FAIL, msg);
    // }
}
