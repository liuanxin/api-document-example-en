package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {

    @ApiReturn("The total number(select count(*) from ...)")
    private int total;

    @ApiReturn("Current page data")
    private List<T> list;
}
