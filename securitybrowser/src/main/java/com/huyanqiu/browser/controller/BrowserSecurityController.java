package com.huyanqiu.browser.controller;

import com.huyanqiu.browser.support.SimpleResponse;
import com.huyanqiu.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Repeatable;

/**
 * 自定义springsecurity跳转登录url
 * @ClassName BrowserSecurityController
 * @author: huyanqiu
 * @since: 2019/2/16 18:25
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 缓存当前请求session信息
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 重定向组件
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 用户自定义配置项
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 拿到引发跳转的请求信息
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是："+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                // 跳转到自定义登录页
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页。");
    }

}
