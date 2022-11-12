package com.github.liuanxin.req;

import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.enums.Gender;
import com.github.liuanxin.enums.ProductType;
import com.github.liuanxin.exception.ServiceException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DemoReq {

    @ApiParam(value = "user id", required = true)
    private Long userId;

    @ApiParam(value = "product type", required = true)
    private ProductType type;

    // Document collection will add enum's { getCode : getValue } information (no splicing name) to the description
    @ApiParam(value = "user gender", dataType = "int")
    private Gender gender;

    @ApiParam(datePattern = "MM/DD/yyyy HH:mm:ss", required = true)
    private Date createTime;

    public void basicCheck() {
        if (userId == null || userId <= 0) {
            throw new ServiceException("Param 'userId' required, and It's was number, and must greater 0");
        }
    }
}
