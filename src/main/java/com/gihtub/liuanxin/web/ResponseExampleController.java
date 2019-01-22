package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/response")
@ApiGroup("response")
public class ResponseExampleController {

    @ApiMethod(title = "response mode", develop = Develop.PRODUCT, index = 3)
    @ApiResponses({
            @ApiResponse(code = 404, msg = "not found"),
            @ApiResponse(code = 200, msg = "success")
    })
    @PostMapping("/demo-object")
    public ResponseEntity<DemoVo> demoObject(@ApiParam(value = "product name", textarea = true) @RequestParam("name") String abc,
                                             @ApiParam(value = "head 1", paramType = ParamType.Header) @RequestHeader Long id,
                                             @ApiParam(value = "head 2", paramType = ParamType.Header) @RequestHeader("some") String xyz,
                                             Page page) {
        if ("abc".equals(abc)) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity<>(new DemoVo(), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(new DemoVo(123L, "Tom", Gender.Male, ProductType.Discount, null));
        }
    }

    @ApiMethod(title = "response List", develop = Develop.PRODUCT, index = 4)
    @ApiResponses({
            @ApiResponse(code = 500, msg = "error"),
            @ApiResponse(code = 200, msg = "success")
    })
    @GetMapping("/demo-list")
    public ResponseEntity<List<DemoVo>> demoList(@ApiParam(value = "product name", textarea = true) String name,
                                                 Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(500).build();
        } else {
            return ResponseEntity.ok(Collections.singletonList(
                    new DemoVo(123L, "Tom", Gender.Male, ProductType.Discount, null)
            ));
        }
    }

    @ApiMethod(title = "response Map", develop = Develop.PRODUCT, index = 5)
    @ApiResponses({
            @ApiResponse(code = 401, msg = "need login"),
            @ApiResponse(code = 200, msg = "success")
    })
    @GetMapping("/demo-map")
    public ResponseEntity<Map<String, DemoVo>> demoMap(@ApiParam(value = "product name", textarea = true) String name,
                                                       Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.ok(Collections.singletonMap(
                    "123", new DemoVo(123L, "张三", Gender.Male, ProductType.Discount, null)
            ));
        }
    }


    @ApiMethod(title = "No way to parse return 1", develop = Develop.PRODUCT, index = 6)
    @GetMapping("/demo-error")
    public Object demoError(@ApiParam(value = "product name", textarea = true) String name, Page page) {
        return new HashMap<>();
    }

    @ApiMethod(title = "No way to parse return 2", develop = Develop.PRODUCT, index = 7)
    @PostMapping("/demo-error2")
    public ResponseEntity demoError2(@ApiParam(value = "product name", textarea = true) String name, Page page) {
        return ResponseEntity.ok(null);
    }

    @ApiMethod(title = "No way to parse return 3", develop = Develop.PRODUCT, index = 7)
    @GetMapping("/demo-error3")
    public ResponseEntity<Object> demoError3(@ApiParam(value = "product name", textarea = true) String name, Page page) {
        return ResponseEntity.ok(new HashMap<>());
    }
}
