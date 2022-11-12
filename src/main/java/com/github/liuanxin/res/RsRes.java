package com.github.liuanxin.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RsRes {

    private int id;
    private String name;
    private RsRes self;


    public static RsRes testData() {
        return new RsRes(111, "aaa", new RsRes().setId(222).setName("bbb"));
    }
}
