package com.huyanqiu.browser.config;

import com.huyanqiu.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.huyanqiu.core.config.AbstractChannelSecurityConfig;
import com.huyanqiu.core.config.ValidateCodeSecurityConfig;
import com.huyanqiu.core.properties.SecurityConstants;
import com.huyanqiu.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 自定义springSecurity表单校验器
 * WebSecurityConfigurerAdapter: web应用安全配置适配器
 * @ClassName BrowerSecurityConfig
 * @author: huyanqiu
 * @since: 2019/2/14 20:05
 */
@Configuration
public class BrowerSecurityConfig extends AbstractChannelSecurityConfig {

    /**
     * security系统自定义配置封装
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 自定义登录逻辑
     */
    @Autowired
    private UserDetailsService myUserDetailsService;

    /**
     * 短信校验过滤器
     */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    /**
     *  验证码校验加载到springsecurity中配置信息
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecurityConfigurer;

    /**
     * 配置springsecurity记住我功能
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 启动时创建persistent_logins表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * 自定义springsecurity密码加密方法
     * 优点：对于同一字符串每次加密后的字符都是不一样的
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用表单登录进行权限验证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        // 表单登录
        http
            // 验证码校验器
            .apply(validateCodeSecurityConfig)
                .and()
            // 加入短信过滤器
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            // spring social配置
            .apply(socialSecurityConfigurer)
                .and()
         // 记住我功能配置
        .rememberMe()
            .tokenRepository(persistentTokenRepository())
            // 记住我功能过期时间
            .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
            // 自定义登录逻辑
            .userDetailsService(myUserDetailsService)
            .and()
        // 下面都是授权的配置
        .authorizeRequests()
        // 指定不需要身份认证url
        .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                securityProperties.getBrowser().getLoginPage(),
                "/code/*", "/index.html").permitAll()
        // ROLE_ADMIN才能访问/user/*下的请求，用户拥有指定的角色权限
//        .antMatchers(HttpMethod.GET, "/user/*").hasRole("ADMIN")
        // 权限表达式(自定义权限校验)
        .anyRequest().access("@reacServiceImpl.hasPermission(request, authentication)")
//        // 都需要身份认证
//                        .authenticated()
        .and()
        // 暂时关闭springsecuritycsrf跨站攻击的功能去掉
        .csrf().disable();
    }

}
