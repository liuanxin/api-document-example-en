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
    // @ApiReturn("Return status, same with response status")
    // private int code;

    @ApiReturn("Return message. for example: address add success. etc...")
    private String msg;

    @ApiReturn("Return data. {\"id\":1} or [{\"id\":1},{\"id\":2}] depending on the specific business")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonResult(String msg) {
        this.msg = msg;
    }
    private JsonResult(String msg, T data) {
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
}
