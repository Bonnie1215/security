package com.huyanqiu.core.properties;

import com.huyanqiu.core.enume.LoginTypeEnum;
import sun.rmi.runtime.Log;

/**
 * web端相关的配置项
 * @ClassName BrowserProperties
 * @author: huyanqiu
 * @since: 2019/2/16 19:37
 */
public class BrowserProperties {

    /**
     * 如果用户(demo)没有配置loginPage，则取browser中signIn.html页面。
     */
    private String loginPage = "/signIn.html";

    /**
     * 默认登录成功/失败返回json格式
     */
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;

    /**
     * springsecurity记住我功能设置1小时后过期
     */
    private int rememberMeSeconds = 3600;

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

}
