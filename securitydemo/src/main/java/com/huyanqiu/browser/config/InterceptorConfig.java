package com.huyanqiu.browser.config;

import com.huyanqiu.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置类
 * @ClassName InterceptorConfig
 * @author: huyanqiu
 * @since: 2019/2/13 19:58
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private MyInterceptor myInterceptor;

    /**
     * 拦截器的注册器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(myInterceptor);
                // 指定的url被拦截器拦截
//                .addPathPatterns("/**");
    }
}
