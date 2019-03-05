package com.gihtub.liuanxin.web;

import com.gihtub.liuanxin.constant.Develop;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.exception.ServiceException;
import com.gihtub.liuanxin.util.Page;
import com.gihtub.liuanxin.vo.DemoVo;
import com.github.liuanxin.api.annotation.*;
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

    @ApiMethod(title = "response mode", develop = Develop.PRODUCT, index = 3)
    @ApiResponses({
            @ApiResponse(code = 404, msg = "not found"),
            @ApiResponse(code = 200, msg = "success")
    })
    @PostMapping("/demo-object")
    public ResponseEntity<DemoVo> demoObject(@ApiParam(value = "product name", textarea = true) @RequestParam("name") String abc,
                                             @ApiParam(value = "head 1", paramType = ParamType.Header) @RequestHeader Long id,
                                             @ApiParam(value = "head 2", paramType = ParamType.Header) @RequestHeader("some") String xyz,
                                             @ApiParam("upload file1") MultipartFile file1,
                                             @ApiParam(value = "upload file2", must = true) MultipartFile file2,
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
        return ResponseEntity.ok(new DemoVo(123L, "Tom", Gender.Male, ProductType.Discount, null));
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
            @ApiResponse(code = 403, msg = "Returns when name was xyz, no permission(redirect to login page)"),
            @ApiResponse(code = 200, msg = "success")
    })
    @GetMapping("/demo-map")
    public ResponseEntity<Map<String, DemoVo>> demoMap(@ApiParam(value = "product name", textarea = true) String name,
                                                       Page page) {
        if ("xyz".equals(name)) {
            return ResponseEntity.status(403).build();
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
