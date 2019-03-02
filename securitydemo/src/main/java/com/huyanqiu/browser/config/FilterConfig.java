package com.huyanqiu.browser.config;

import com.huyanqiu.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 手动加上一个过滤器
 * @ClassName FilterConfig
 * @author: huyanqiu
 * @since: 2019/2/13 19:38
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // 创建过滤器注册对象
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        // 创建自定义过滤器
        MyFilter myFilter = new MyFilter();
        // 将自定义的过滤器放到项目中
        filterBean.setFilter(myFilter);
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        // 设置过滤器拦截的请求URL
        filterBean.setUrlPatterns(urlList);
        return filterBean;
    }

}
