package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.constant.Const;
import com.gihtub.liuanxin.util.JsonCode;
import com.github.liuanxin.api.annotation.EnableApiInfo;
import com.github.liuanxin.api.annotation.ParamType;
import com.github.liuanxin.api.model.DocumentCopyright;
import com.github.liuanxin.api.model.DocumentParam;
import com.github.liuanxin.api.model.DocumentResponse;
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
                .setOnline(online);
    }

    private Set<String> ignoreUrl() {
        Set<String> urlSet = new HashSet<>();
        urlSet.add("/error");
        return urlSet;
    }

    private List<DocumentResponse> globalResponse() {
        List<DocumentResponse> responseList = new ArrayList<>();
        for (JsonCode code : JsonCode.values()) {
            responseList.add(new DocumentResponse(code.getCode(), code.getValue()));
        }
        return responseList;
    }

    private List<DocumentParam> tokens() {
        return Arrays.asList(
                DocumentParam.buildToken("x-auth-token", "用户认证数据", "abc-xyz", ParamType.Header).setHasTextarea(true),
                DocumentParam.buildToken("x-version", "接口版本", "1.0.0", ParamType.Query).setMust(true)
        );
    }
}
