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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                .setGlobalToken(token())
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
            responseList.add(new DocumentResponse(code.getFlag(), code.getMsg()));
        }
        return responseList;
    }

    private DocumentParam token() {
        String name = "x-token", desc = "user oauth info", example = "abc-123-xyz";
        return DocumentParam.buildToken(name, desc, example, ParamType.Header)/*.setMust(true).setHasTextarea(true)*/;
    }
}
