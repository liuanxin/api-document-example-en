package com.github.liuanxin.req;

import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyReq {

    @ApiParam(value = "user id", required = true)
    private Long userId;

    @ApiParam(value = "gender", dataType = "int", style = "color:green")
    private Gender gender;
}
