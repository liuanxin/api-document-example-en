package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageInfo<T> {

    @ApiReturn("The total number(select count(*) from ...)")
    private int total;

    @ApiReturn("Current page data")
    private List<T> list;
}
