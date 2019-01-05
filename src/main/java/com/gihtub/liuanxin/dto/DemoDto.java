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

    // Document collection will add enum's { getCode : getValue } information (no splicing name) to the description
    @ApiParam(value = "user gender", dataType = "int")
    private Gender gender;
}
