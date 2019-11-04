package com.github.liuanxin.web;

import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.api.annotation.ApiTokens;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.dto.DemoDto;
import com.github.liuanxin.enums.ProductType;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
import com.github.liuanxin.vo.DemoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@ApiGroup(value = Develop.PRODUCT_DESC, index = 2)
public class ProductExampleController {

    @ApiMethod(value = "product list(Pagination)", develop = Develop.PRODUCT, index = -1)
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> page(@ApiParam(value = "product name", textarea = true) String name, Page page) {
        return JsonResult.success("list", DemoVo.testPageData());
    }

    @ApiMethod(value = "product detail(Model)", develop = Develop.PRODUCT, index = 0)
    @GetMapping("/{id}")
    public JsonResult<DemoVo> id(@PathVariable("id") @ApiParam("product id") Long id) {
        return JsonResult.success("detail", DemoVo.testData());
    }

    @ApiTokens
    @ApiMethod(value = "product list(List)", develop = Develop.PRODUCT, index = 1)
    @GetMapping("/list")
    public JsonResult<List<DemoVo>> list(@ApiParam("product type") ProductType type) {
        return JsonResult.success("list", DemoVo.testListData());
    }

    @ApiMethod(value = "product xxx(Map)", develop = Develop.PRODUCT, index = 2)
    @GetMapping("/map")
    public JsonResult<Map<String, DemoVo>> map(@ApiParam("yy") Long id, DemoDto demoDto) {
        demoDto.basicCheck();
        return JsonResult.success("map", DemoVo.testMapData());
    }
}
