package com.huyanqiu.core.properties;

/**
 * social配置基类
 * @ClassName SocialProperties
 * @author: huyanqiu
 * @since: 2019/2/24 13:56
 */
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
