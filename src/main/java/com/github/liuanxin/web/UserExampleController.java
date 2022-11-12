package com.github.liuanxin.web;

import com.github.liuanxin.api.annotation.*;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.enums.UserType;
import com.github.liuanxin.exception.ServiceException;
import com.github.liuanxin.req.DemoReq;
import com.github.liuanxin.req.RequestBodyInnerReq;
import com.github.liuanxin.req.RequestBodyReq;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiTokens
@RestController
@RequestMapping("/user")
@ApiGroup(value = Develop.USER_DESC, index = 1)
public class UserExampleController {

    @ApiMethod(value = "user list", develop = Develop.USER, index = 1, desc = "manager query")
    @GetMapping
    public JsonResult<PageInfo<DemoRes>> demo1(DemoReq demoReq, Page page) {
        return JsonResult.success("test1");
    }

    @ApiMethod(value = "user info", develop = Develop.USER, index = 2, commentInReturnExample = false)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoRes>> demo2(@ApiParam("user type") UserType type) {
        return JsonResult.success("test2");
    }

    @ApiMethod(value = "user detail", develop = Develop.USER, desc = "when use click own avatar")
    @ApiResponses({
            @ApiResponse(code = 200, msg = "success, operate data"),
            @ApiResponse(code = 500, msg = "error, show response body to customer")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoRes> demo3(@PathVariable("id") @ApiParam(value = "user id", example = "1") Long id) {
        return JsonResult.success("test3");
    }

    @ApiMethod(value = "user operate", develop = Develop.USER)
    @PostMapping("/operate")
    public JsonResult<PageInfo<DemoRes>> demo4(
            @ApiParam("move type(0 means from top to bottom, 1 means from bottom to top, the default is 0)") Boolean type) {
        if (type != null && !type) {
            throw new ServiceException("operate error");
        }
        return JsonResult.success("test4");
    }

    @ApiMethod(value = "user RequestBody", develop = Develop.USER)
    @PostMapping("/detail")
    public JsonResult<List<DemoRes>> demo5(@RequestBody RequestBodyReq demoDto) {
        return JsonResult.success("test5");
    }

    @ApiMethod(value = "user RequestBody(inner)", develop = Develop.USER)
    @PostMapping("/add-info")
    public JsonResult<List<DemoRes>> demo6(@RequestBody RequestBodyInnerReq demoDto) {
        return JsonResult.success("test6", DemoRes.testListData());
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoRes>> demo4(String name, DemoReq demoReq) {
        return JsonResult.success("test4");
    }
}
