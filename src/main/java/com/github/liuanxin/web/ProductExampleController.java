package com.github.liuanxin.web;

import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.api.annotation.ApiTokens;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.enums.ProductType;
import com.github.liuanxin.req.DemoReq;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
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
    public JsonResult<PageInfo<DemoRes>> page(@ApiParam(value = "product name", textarea = true) String name, Page page) {
        return JsonResult.success("list", DemoRes.testPageData());
    }

    @ApiMethod(value = "product detail(Model)", develop = Develop.PRODUCT, index = 0)
    @GetMapping("/{id}")
    public JsonResult<DemoRes> id(@PathVariable("id") @ApiParam("product id") Long id) {
        return JsonResult.success("detail", DemoRes.testData());
    }

    @ApiTokens
    @ApiMethod(value = "product list(List)", develop = Develop.PRODUCT, index = 1)
    @GetMapping("/list")
    public JsonResult<List<DemoRes>> list(@ApiParam("product type") ProductType type) {
        return JsonResult.success("list", DemoRes.testListData());
    }

    @ApiMethod(value = "product xxx(Map)", develop = Develop.PRODUCT, index = 2)
    @GetMapping("/map")
    public JsonResult<Map<String, DemoRes>> map(@ApiParam("yy") Long id, DemoReq demoReq) {
        demoReq.basicCheck();
        return JsonResult.success("map", DemoRes.testMapData());
    }
}
