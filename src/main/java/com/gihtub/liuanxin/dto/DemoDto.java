package com.gihtub.liuanxin.dto;

import com.gihtub.liuanxin.enums.Gender;
import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoDto {

    @ApiParam(value = "user id", must = true)
    private Long userId;

    @ApiParam(value = "user gender", dataType = "int")
    private Gender gender;
}
