package com.huyanqiu.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 短信登录配置类
 * @ClassName SmsCodeAuthenticationSecurityConfig
 * @author: huyanqiu
 * @since: 2019/2/23 15:34
 */
@Configuration
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

    /**
     * 自定义成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    /**
     * 自定义失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 自定义查询用户信息
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 1、自定义短信过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        // 2、设置AuthenticationManager调用support方法获取对应类型的AuthenticationProvider
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(
                AuthenticationManager.class));
        // 3、统一设置成功处理和失败处理业务
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        // 4、配置AuthenticationProvider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider =
                new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        // 5、加入到springsecurity安全框架中
        http.authenticationProvider(smsCodeAuthenticationProvider)
                // 自定义短信校验过滤器加到UsernamePasswordAuthenticationFilter后面
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
