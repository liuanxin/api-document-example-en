package com.gihtub.liuanxin.vo;

import com.gihtub.liuanxin.enums.Gender;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoVo {

    @ApiReturn("user id")
    private String userId;

    @ApiReturn(value = "user gender", type = "int")
    private Gender gender;
}
