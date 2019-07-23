package com.gihtub.liuanxin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoVo {

    @ApiReturn(value = "user id", example = "123")
    private Long id;

    @ApiReturn(value = "user name", example = "Tom")
    private String name;

    // Document collection will add enum's { getCode : getValue } information (no splicing name) to the description
    @ApiReturn(value = "user gender", type = "int")
    private Gender gender;

    @ApiReturn(value = "product type", type = "int")
    private ProductType type;

    @ApiReturn(value = "return Map example, ignore return if null or empty")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<Integer, DemoOtherVo> map;


    @Getter
    @Setter
    public static class DemoOtherVo {
        @ApiReturn(value = "other id", example = "12345")
        private Long otherId;

        @ApiReturn(value = "description...", example = "one two three")
        private String desc;
    }
}
