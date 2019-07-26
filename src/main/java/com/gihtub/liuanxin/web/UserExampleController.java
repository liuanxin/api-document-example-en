package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.dto.DemoDto;
import com.gihtub.liuanxin.enums.UserType;
import com.gihtub.liuanxin.exception.ServiceException;
import com.gihtub.liuanxin.util.JsonResult;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.util.PageInfo;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.springframework.web.bind.annotation.*;

@ApiTokens(false)
@RestController
@RequestMapping("/user")
@ApiGroup(value = Develop.USER_DESC, index = 1)
public class UserExampleController {

    @ApiMethod(value = "user list", develop = Develop.USER, index = 1, desc = "manager query")
    @GetMapping
    public JsonResult<PageInfo<DemoVo>> demo1(DemoDto demoDto, Page page) {
        return JsonResult.success("test1");
    }

    @ApiMethod(value = "user info", develop = Develop.USER, index = 2, commentInReturnExample = false)
    @GetMapping("/info")
    public JsonResult<PageInfo<DemoVo>> demo2(@ApiParam("user type") UserType type) {
        return JsonResult.success("test2");
    }

    @ApiMethod(value = "user detail", develop = Develop.USER, desc = "when use click own avatar")
    @ApiResponses({
            @ApiResponse(code = 200, msg = "success, operate data"),
            @ApiResponse(code = 500, msg = "error, show response body to customer")
    })
    @PostMapping("/{id}")
    public JsonResult<DemoVo> demo3(@PathVariable("id") @ApiParam(value = "user id", example = "1") Long id) {
        return JsonResult.success("test3");
    }

    @ApiMethod(value = "user operate", develop = Develop.USER)
    @PostMapping("/operate")
    public JsonResult<PageInfo<DemoVo>> demo4(
            @ApiParam("move type(0 means from top to bottom, 1 means from bottom to top, the default is 0)") Boolean type) {
        if (type != null && !type) {
            throw new ServiceException("operate error");
        }
        return JsonResult.success("test4");
    }

    @ApiIgnore
    @GetMapping("/ignore-demo")
    public JsonResult<PageInfo<DemoVo>> demo4(String name, DemoDto demoDto) {
        return JsonResult.success("test4");
    }
}
