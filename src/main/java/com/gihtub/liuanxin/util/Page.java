package com.gihtub.liuanxin.util;

import com.github.liuanxin.api.annotation.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

    @ApiParam("Current page, default is 1")
    private Integer page;

    @ApiParam("Number of pages per page. default is 15")
    private Integer limit;
}
