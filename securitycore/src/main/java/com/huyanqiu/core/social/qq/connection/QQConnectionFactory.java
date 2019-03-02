package com.huyanqiu.core.social.qq.connection;

import com.huyanqiu.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * QQConnectionFactory
 * @ClassName QQConnectionFactory
 * @author: huyanqiu
 * @since: 2019/2/24 13:33
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * 构造方法
     * @param providerId 提供商的唯一标识
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret ) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
    
}
