package com.huyanqiu.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * QQ获取用户信息实现类
 * @ClassName QQImpl
 * @author: huyanqiu
 * @since: 2019/2/24 12:29
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取用户信息的URL
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    /**
     * 获取openId的URL
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 服务器分配
     */
    private String appId;
    /**
     * 用户ID，与QQ号码一一对应
     */
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        // 父类会自动处理accessToken参数，挂在请求参数上
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("获取openId返回结果："+result);
        this.openId = StringUtils.substringBetween(result, "\"open\"", "}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("获取QQ用户信息：" + result);
        QQUserInfo qqUserInfo = new QQUserInfo();
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("获取用户信息失败");
        } finally {
            return qqUserInfo;
        }
    }

}
