package com.huyanqiu.core.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 短信验证自定义token
 * @ClassName SmsCodeAuthenticationToken
 * @author: huyanqiu
 * @since: 2019/2/23 14:51
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;

    /**
     * 构造方法（未认证通过调用该构造方法）
     * @param mobile 手机号
     */
    public SmsCodeAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        // 未认证通过
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * 构造方法（认证通过调用该构造方法）
     * @param principal 用户信息
     * @param authorities 用户拥有的权限
     */
    public SmsCodeAuthenticationToken(Object principal,
            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        // 认证通过
        super.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
