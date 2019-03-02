package com.huyanqiu.core.social.qq.connection;

import com.huyanqiu.core.social.qq.api.QQ;
import com.huyanqiu.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * ConnectionFactory中的ApiAdapter
 * @ClassName QQAdapter
 * @author: huyanqiu
 * @since: 2019/2/24 13:20
 */
public class QQAdapter implements ApiAdapter<QQ>{

    /**
     * 判断QQ服务器是否是通的
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 在ConnectionFactory和Api中做一个适配，把ConnectionValues需要的参数都设置上
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        // 获取QQ的用户信息
        QQUserInfo userInfo = api.getUserInfo();
        // 显示出来的用户名字
        values.setDisplayName(userInfo.getNickname());
        // 用户头像
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 个人主页
        values.setProfileUrl(null);
        // 服务商的用户Id(openId)
        values.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 通过API拿到标准用户信息
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
