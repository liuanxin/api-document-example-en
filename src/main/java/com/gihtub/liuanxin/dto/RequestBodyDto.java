package com.gihtub.liuanxin.dto;

import com.gihtub.liuanxin.enums.Gender;
import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyDto {

    @ApiParam(value = "用户 id", must = true)
    private Long userId;

    @ApiParam(value = "性别", dataType = "int", style = "color:green")
    private Gender gender;
}