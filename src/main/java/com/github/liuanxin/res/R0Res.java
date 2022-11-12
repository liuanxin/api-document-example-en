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
public class R0Res {

    private int id;
    private R10Vo r10;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class R10Vo {

        private int id;
        private R0Res r0;
    }


    public static R0Res testData() {
        return new R0Res(111, new R10Vo(333, null));
    }
}
