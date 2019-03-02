package com.huyanqiu.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 自定义短信验证provider
 * @ClassName SmsCodeAuthenticationProvider
 * @author: huyanqiu
 * @since: 2019/2/23 15:12
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider{

    /**
     * 自定义查询出的用户信息
     */
    private UserDetailsService userDetailsService;

    /**
     * 进行身份认证逻辑处理
     * @param authentication 封装请求认证信息SmsCodeAuthenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        // 自定义用户查询(根据手机号查询用户信息)
        UserDetails user =
                userDetailsService.loadUserByUsername((String) smsCodeAuthenticationToken.getPrincipal());

        if (user==null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        // 将认证通过的信息放到SmsCodeAuthenticationToken中
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        // 将未认证的details复制到已认证的details中
        authenticationResult.setDetails(authentication.getDetails());
        return authenticationResult;
    }

    /**
     * 根据请求封装的token类型判断调用具体的provider
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断传将来的认证是否是SmsCodeAuthenticationToken类型
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
}
