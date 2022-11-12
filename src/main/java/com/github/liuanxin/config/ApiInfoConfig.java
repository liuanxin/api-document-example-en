package com.github.liuanxin.config;

import com.github.liuanxin.api.annotation.EnableApiInfo;
import com.github.liuanxin.api.model.DocumentCopyright;
import com.github.liuanxin.api.model.DocumentParam;
import com.github.liuanxin.api.model.DocumentResponse;
import com.github.liuanxin.constant.Const;
import com.github.liuanxin.res.DemoRes;
import com.github.liuanxin.util.JsonResult;
import com.github.liuanxin.util.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@EnableApiInfo
public class ApiInfoConfig {

    @Value("${online:false}")
    private boolean online;

    @Bean
    public DocumentCopyright apiCopyright() {
        return new DocumentCopyright()
                .setTitle(Const.PROJECT_TITLE)
                .setTeam(Const.PROJECT_CONTACT)
                .setVersion(Const.PROJECT_VERSION)
                .setCopyright(Const.PROJECT_COPYRIGHT)
                .setIgnoreUrlSet(ignoreUrl())
                .setGlobalResponse(globalResponse())
                .setGlobalTokens(tokens())
                .setProjectMap(new LinkedHashMap<String, String>() {{
                    put("cn-中文接口示例", "http://127.0.0.1:8080");
                }})
                .setOnline(online);
    }

    private Set<String> ignoreUrl() {
        Set<String> urlSet = new HashSet<>();
        urlSet.add("/error");
        return urlSet;
    }

    private List<DocumentResponse> globalResponse() {
        /*
        List<DocumentResponse> responseList = new ArrayList<>();
        for (JsonCode code : JsonCode.values()) {
            responseList.add(new DocumentResponse(code.getCode(), code.getValue()));
        }
        */
        List<DocumentResponse> responseList = new ArrayList<>();
        responseList.add(new DocumentResponse(200, "success"));
        responseList.add(new DocumentResponse(400, "abc").setResponse(DemoRes.class));
        responseList.add(new DocumentResponse(501, "xxx").setResponse(
                Map.class, new Class[] { String.class, DemoRes.class }
        ));
        responseList.add(new DocumentResponse(503, "yyy").setResponse(
                JsonResult.class, List.class, new Class[] { Map.class }, new Class[] { String.class, DemoRes.class }
        ));
        responseList.add(new DocumentResponse(500, "zzz").setResponse(
                JsonResult.class, PageInfo.class, new Class[] { DemoRes.class }
        ));
        return responseList;
    }

    private List<DocumentParam> tokens() {
        return Arrays.asList(
                DocumentParam.buildToken("x-token", "user oauth", "", true)
//                , DocumentParam.buildToken("x-version", "api version", Const.PROJECT_VERSION, false).setParamType(ParamType.Query.name())
        );
    }
}
