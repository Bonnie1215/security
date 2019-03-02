package com.huyanqiu.core.social.qq.config;

import com.huyanqiu.core.properties.SecurityProperties;
import com.huyanqiu.core.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 配置类，当系统中配置my.security.social.qq.app-id参数，配置类才会被加载
 * @ClassName QQAutoConfig
 * @author: huyanqiu
 * @since: 2019/2/24 14:03
 */
@Configuration
@ConditionalOnProperty(prefix = "my.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        String providerId = securityProperties.getSocial().getQq().getProviderId();
        String appId = securityProperties.getSocial().getQq().getAppId();
        String appSecret = securityProperties.getSocial().getQq().getAppSecret();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }

}
