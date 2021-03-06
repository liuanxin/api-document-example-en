package com.github.liuanxin.config;

import com.github.liuanxin.util.CorsFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

@Configuration
@ConditionalOnClass({ Filter.class })
public class GlobalWebConfig {

    // cross-domain
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> characterFilter() {
        FilterRegistrationBean<CharacterEncodingFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.displayName(), true));
        return filterBean;
    }
}
