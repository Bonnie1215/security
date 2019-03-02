package com.huyanqiu.core.properties;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

/**
 * 短信验证码自定义参数
 * @ClassName SmsCodeProperties
 * @author: huyanqiu
 * @since: 2019/2/23 10:09
 */
public class SmsCodeProperties {

    /**
     * 短信验证码长度
     */
    private int length = 6;
    /**
     * 短信验证码失效时间
     */
    private int exprieIn = 60;

    /**
     * 需要拦截的URL
     */
    private String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExprieIn() {
        return exprieIn;
    }

    public void setExprieIn(int exprieIn) {
        this.exprieIn = exprieIn;
    }

}
