package com.huyanqiu.core.social.qq.api;

import java.io.IOException;

/**
 * 自定义获取服务器用户信息
 * @ClassName QQ
 * @author: huyanqiu
 * @since: 2019/2/24 12:25
 */
public interface QQ {

    /**
     * 获取用户信息
     * @return
     */
    QQUserInfo getUserInfo();

}
