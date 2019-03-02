package com.huyanqiu.browser.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义springsecurity用户信息获取逻辑
 * @ClassName MyUserDetailsService
 * @author: huyanqiu
 * @since: 2019/2/16 15:27
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 表单登录：自定义用户信息获取逻辑
     * @param username 登录用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名："+username);
        return build(username);
    }

    /**
     * 社交登录：微信登录、QQ登录
     * @param userId 存在userconnection表中的userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId)
            throws UsernameNotFoundException {
        logger.info("社交登录用户名："+userId);
        return build(userId);
    }

    private SocialUserDetails build(String userId) {
        // 密码，一般是在用户注册对密码加密，这里简化操作没有在数据库中取。
        String password = passwordEncoder.encode("123456");
        // 账户是否可用(被删除)
        boolean enabled = true;
        // 账户是否过期
        boolean accountNonExpired = true;
        // 密码是否过期
        boolean credentialsNonExpired = true;
        // 账户是否被锁定
        boolean accountNonLocked = true;
        // 用户拥有权限
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        logger.info("数据库密码是："+password);
        // TODO 根据用户名在数据库中查找相关的信息
        return new SocialUser(userId, password, enabled,
                accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }
}
