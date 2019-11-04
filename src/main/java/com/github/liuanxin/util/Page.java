package com.github.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    @ApiParam("Current page")
    private Integer page;

    @ApiParam("Number of pages per page")
    private Integer limit;
}
