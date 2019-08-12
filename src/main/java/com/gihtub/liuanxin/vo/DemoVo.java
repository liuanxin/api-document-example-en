package com.gihtub.liuanxin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gihtub.liuanxin.enums.Gender;
import com.gihtub.liuanxin.enums.ProductType;
import com.gihtub.liuanxin.enums.UserType;
import com.gihtub.liuanxin.util.PageInfo;
import com.github.liuanxin.api.annotation.ApiReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoVo {

    @ApiReturn(value = "user id", example = "123")
    private Long id;

    @ApiReturn(value = "user name", example = "Tom")
    private String name;

    // Document collection will add enum's { getCode : getValue } information (no splicing name) to the description
    @ApiReturn(value = "user gender", type = "int")
    private Gender gender;

    // in example page, this value will output null
    // Non-base data type arrays also cause output of warn information
    // which can be replaced with List<ProductType>
    @ApiReturn(value = "product type", type = "int")
    private ProductType[] productTypes;
    // private List<ProductType> productTypes;

    @ApiReturn("user type")
    private List<UserType> userTypes;

    @ApiReturn(value = "a List example")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DemoOneVo> ones;

    @ApiReturn(value = "a Map example")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<Integer, DemoTwoVo> twos;

    @ApiReturn(value = "a Map")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, List<DemoThreeVo>> threes;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoOneVo {
        @ApiReturn(value = "one id", example = "111")
        private Long oneId;

        @ApiReturn(value = "str", example = "000")
        private String one;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoTwoVo {
        @ApiReturn(value = "two id", example = "222")
        private Long twoId;

        @ApiReturn(value = "array", example = "abc")
        private String[] twoArray;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoThreeVo {
        @ApiReturn(value = "three id", example = "222")
        private Long threeId;

        @ApiReturn(value = "list", example = "xyz")
        private List<String> threeList;
    }


    public static DemoVo testData() {
        return new DemoVo(
                123L, "Tom", Gender.Male,
                new ProductType[] { ProductType.Normal, ProductType.Discount },
                // Arrays.asList(ProductType.Normal, ProductType.Discount),
                Arrays.asList(UserType.Normal, UserType.Vip),
                Arrays.asList(new DemoOneVo(13579L, "one"), new DemoOneVo(24680L, "two")),
                new HashMap<Integer, DemoTwoVo>() {{
                    put(111, new DemoTwoVo(147L, new String[] { "four", "seven" }));
                    put(444, new DemoTwoVo(369L, new String[] { "siz", "nine" }));
                }},
                new HashMap<String, List<DemoThreeVo>>() {{
                    put("name", Arrays.asList(
                            new DemoThreeVo(159L, Arrays.asList("five", "nine")),
                            new DemoThreeVo(357L, Arrays.asList("three", "seven"))
                    ));
                }}
        );
    }
    public static List<DemoVo> testListData() {
        return Arrays.asList(testData(), new DemoVo(
                321L, "Jerry", Gender.Female,
                new ProductType[] { ProductType.Discount },
                // Arrays.asList(ProductType.Discount),
                Arrays.asList(UserType.Normal, UserType.Vip),
                Arrays.asList(new DemoOneVo(97531L, "zero"), new DemoOneVo(86420L, "ten")),
                new HashMap<Integer, DemoTwoVo>() {{
                    put(222, new DemoTwoVo(741L, new String[] { "seven", "four" }));
                    put(333, new DemoTwoVo(963L, new String[] { "nine", "six" }));
                }},
                new HashMap<String, List<DemoThreeVo>>() {{
                    put("id", Arrays.asList(
                            new DemoThreeVo(951L, Arrays.asList("nine", "five")),
                            new DemoThreeVo(753L, Arrays.asList("seven", "three"))
                    ));
                }}
        ));
    }
    public static PageInfo<DemoVo> testPageData() {
        return new PageInfo<>(100, testListData());
    }
    public static Map<String, DemoVo> testMapData() {
        return new HashMap<String, DemoVo>() {{ put("some", testData()); }};
    }
}
