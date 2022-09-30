package com.github.liuanxin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.liuanxin.api.annotation.ApiReturn;
import com.github.liuanxin.enums.Gender;
import com.github.liuanxin.enums.ProductType;
import com.github.liuanxin.enums.UserType;
import com.github.liuanxin.util.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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
    @ApiReturn(value = "user gender", example = "1")
    private Gender gender;

    @ApiReturn(value = "product type", example = "discount")
    private ProductType[] productTypes;

    @ApiReturn(value = "user type", example = "vip")
    private List<UserType> userTypes;

    @ApiReturn("a List example")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DemoOneVo> ones;

    @ApiReturn("a Map example, key is int, still output as \"0\" string when serializing")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<Integer, DemoTwoVo> twos;

    @ApiReturn("a Map, key is a String, which is output as \"?\" when serialized")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, List<DemoThreeVo>> threes;

    @ApiReturn(value = "time1", example = "01/13/2018 12:13:14")
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date createTime;

    @ApiReturn(value = "time2", example = "2019-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoOneVo {
        @ApiReturn(value = "one id", example = "111")
        private Long oneId;

        @ApiReturn(value = "str", example = "one one one")
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
                }},
                new Date(),
                new Date()
        );
    }
    public static List<DemoVo> testListData() {
        return Arrays.asList(testData(), new DemoVo(
                321L, "Jerry", Gender.Female,
                new ProductType[] { ProductType.Discount },
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
                }},
                new Date(),
                new Date()
        ));
    }
    public static PageInfo<DemoVo> testPageData() {
        return new PageInfo<>(100, testListData());
    }
    public static Map<String, DemoVo> testMapData() {
        return new HashMap<String, DemoVo>() {{ put("some", testData()); }};
    }
}
