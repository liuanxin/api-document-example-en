package com.github.liuanxin.req;

import com.github.liuanxin.api.annotation.ApiParam;
import com.github.liuanxin.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RequestBodyInnerReq {

    @ApiParam("user id")
    private Long id;

    @ApiParam(dataType = "int", style = "color:green")
    private Gender gender;

    private AddressReq address;

    private List<OrderReq> orderList;

    private Map<String, OtherReq> otherMap;

    @Getter
    @Setter
    public static class AddressReq {

        @ApiParam(value = "address id", required = true)
        private Long id;

        private String address;
    }

    @Getter
    @Setter
    public static class OrderReq {

        @ApiParam(value = "order id", required = true)
        private Long id;

        private BigDecimal totalAmount;

        private List<OrderItemReq> itemList;
    }

    @Getter
    @Setter
    public static class OrderItemReq {

        @ApiParam(value = "order item id", required = true)
        private Long id;

        private String productName;

        private BigDecimal price;

        private Integer number;
    }

    @Getter
    @Setter
    public static class OtherReq {

        @ApiParam(value = "other id", required = true)
        private Long id;

        private String other;
    }
}
