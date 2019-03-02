package com.huyanqiu.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 社交登录QQ自定义参数配置信息
 * @ClassName QQProperties
 * @author: huyanqiu
 * @since: 2019/2/24 13:54
 */
public class QQProperties extends SocialProperties{

    /**
     * 服务提供商标识
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
