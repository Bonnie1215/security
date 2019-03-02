package com.huyanqiu.core.social.qq.connection;

import com.huyanqiu.core.social.qq.api.QQ;
import com.huyanqiu.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * QQServerProvider
 * @ClassName QQServerProvider
 * @author: huyanqiu
 * @since: 2019/2/24 13:16
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;

    /**
     * 引导用户跳转的地址
     */
    private static final String  URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    /**
     * 获取令牌的地址
     */
    private static final String  URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        // authorizeUrl：将用户导向认证服务器的URL accessTokenUrl：申请令牌URL
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }

}
