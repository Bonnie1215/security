package com.huyanqiu.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * security系统配置封装
 * ConfigurationProperties注解：表示SecurityProperties类会读取my.security开头的所有配置项
 * @ClassName SecurityProperties
 * @author: huyanqiu
 * @since: 2019/2/16 19:32
 */
@ConfigurationProperties(prefix = "my.security")
public class SecurityProperties {

    /**
     * web端相关的系统配置，读取my.security.browser的配置项
     */
    private BrowserProperties browser = new BrowserProperties();
    /**
     * web端相关的系统配置，读取my.security.code的配置项
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
    /**
     * social相关的系统配置，读取my.security.social的配置项
     */
    private SocialProperties social = new SocialProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
