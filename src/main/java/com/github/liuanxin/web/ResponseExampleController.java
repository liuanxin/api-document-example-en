package com.github.liuanxin.web;

import com.github.liuanxin.api.annotation.*;
import com.github.liuanxin.constant.Develop;
import com.github.liuanxin.enums.Gender;
import com.github.liuanxin.exception.ServiceException;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.res.R0Res;
import com.github.liuanxin.res.R1Res;
import com.github.liuanxin.res.RsRes;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.Page;
import com.github.liuanxin.util.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/response")
@ApiGroup("response")
public class ResponseExampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExampleController.class);

    @ApiMethod(value = "response mode", develop = Develop.PRODUCT, index = 1)
    @ApiResponses({
            @ApiResponse(code = 200, msg = "success"),
            @ApiResponse(code = 404, msg = "not found")
    })
    @PostMapping("/demo-object")
    public ResponseEntity<DemoRes> demoObject(@ApiParam(value = "product name", textarea = true) @RequestParam("name") String abc,
                                              @ApiParam(value = "head 1", paramType = ParamType.Header) @RequestHeader Long id,
                                              @ApiParam(value = "head 2", paramType = ParamType.Header) @RequestHeader("some") String xyz,
                                              @ApiParam(value = "", example = "2") Gender gender,
                                              @ApiParam("upload file1") MultipartFile file1,
                                              @ApiParam(value = "upload file2", required = true) MultipartFile file2,
                                              Page page) {
        if ("abc".equals(abc)) {
            throw new ServiceException("product name error");
        }
        if (id == null || id < 0) {
            throw new ServiceException("head1 can't null and can't less 0");
        }
        if (xyz == null || "".equals(xyz.trim())) {
            throw new ServiceException("head2 can't null");
        }
        if (file2 == null) {
            throw new ServiceException("upload file2 can't null");
        }

        String fileName = file2.getOriginalFilename();
        if (fileName == null || !fileName.matches("(?i)^(.*)\\.(ico|jpeg|jpg|bmp|png)$")) {
            throw new ServiceException("upload file2 must has image");
        }

        // save file
        try {
            String saveFile = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
            file2.transferTo(new File("/" + saveFile));
        } catch (IOException e) {
            String msg = "save file exception";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(msg, e);
            }
            throw new ServiceException(msg);
        }

        // it's ok
        return ResponseEntity.ok(DemoRes.testData());
    }

    @ApiMethod(value = "response List", develop = Develop.PRODUCT, index = 2, commentInReturnExample = false)
    @ApiResponses({
            @ApiResponse(code = 200, msg = "success"),
            @ApiResponse(code = 500, msg = "Returns when name is xyz, error", type = {
                    @ApiReturnType(value = JsonResult.class, genericParent = PageInfo.class, generic = DemoRes.class)
            })
    })
    @GetMapping("/demo-list")
    public ResponseEntity<List<DemoRes>> demoList(@ApiParam(value = "product name", textarea = true) String name,
                                                  Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(500).build();
        } else {
            return ResponseEntity.ok(DemoRes.testListData());
        }
    }

    @ApiMethod(value = "response Map", develop = Develop.PRODUCT, index = 3)
    @ApiResponses({
            @ApiResponse(code = 200, msg = "success"),
            @ApiResponse(code = 403, msg = "Returns when name is xyz, no permission(redirect to login page)")
    })
    @GetMapping("/demo-map")
    public ResponseEntity<Map<String, DemoRes>> demoMap(@ApiParam(value = "product name", textarea = true) String name,
                                                        Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(403).build();
        } else {
            return ResponseEntity.ok(DemoRes.testMapData());
        }
    }


    @ApiMethod(value = "Customize response 1", develop = Develop.PRODUCT, index = 4, commentInReturnExampleWithLevel = false, returnType = {
            @ApiReturnType(value = JsonResult.class, genericParent = Map.class, generic = {String.class, DemoRes.class })
    })
    @GetMapping("/demo-custom1")
    public Object custom1() {
        return new HashMap<>();
    }

    @ApiMethod(value = "Customize response 2", develop = Develop.PRODUCT, index = 5, returnType = {
            @ApiReturnType(value = JsonResult.class, genericParent = PageInfo.class, generic = List.class, genericChild = DemoRes.class)
    })
    @PostMapping("/demo-custom2")
    public ResponseEntity custom2(@ApiParam(value = "product name", textarea = true) String name) {
        return ResponseEntity.ok(null);
    }


    @ApiMethod(value = "recursive 1", develop = Develop.PRODUCT, index = 6)
    @GetMapping("/demo-recursive1")
    public JsonResult<RsRes> recursive1() {
        return JsonResult.success("r1", RsRes.testData());
    }
    @ApiMethod(value = "recursive 2", develop = Develop.PRODUCT, index = 7)
    @GetMapping("/demo-recursive2")
    public JsonResult<R0Res> recursive2(@ApiParam(value = "product name", textarea = true) String name) {
        return JsonResult.success("r2", R0Res.testData());
    }
    @ApiMethod(value = "recursive 3", develop = Develop.PRODUCT, index = 8)
    @GetMapping("/demo-recursive3")
    public ResponseEntity<R1Res> recursive3(@ApiParam(value = "product name", textarea = true) String name, Page page) {
        return ResponseEntity.ok(R1Res.testData());
    }


    // The following return results cannot be parsed

    @ApiMethod(value = "No way to parse return 1", develop = Develop.PRODUCT)
    @GetMapping("/demo-error1")
    public ResponseEntity demoError1(@ApiParam(datePattern = "MM/DD/yyyy") Date create) {
        return ResponseEntity.ok(new HashMap<>());
    }

    @ApiMethod(value = "No way to parse return 2", develop = Develop.PRODUCT)
    @GetMapping("/demo-error2")
    public ResponseEntity<Object> demoError2(
            @ApiParam(dataType = "date", datePattern = "HH:mm:ss") String time,
            Page page) {
        return ResponseEntity.ok(new HashMap<>());
    }
}
