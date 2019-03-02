package com.huyanqiu.browser.anthentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huyanqiu.browser.support.SimpleResponse;
import com.huyanqiu.core.enume.LoginTypeEnum;
import com.huyanqiu.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录自定义登录失败访问请求
 * 1、实现AuthenticationFailureHandler接口
 * 2、继承SimpleUrlAuthenticationFailureHandler
 * @ClassName MyAuthenticationFailureHandler
 * @author: huyanqiu
 * @since: 2019/2/16 20:34
 */
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");

        LoginTypeEnum loginType = securityProperties.getBrowser().getLoginType();
        if (LoginTypeEnum.JSON.equals(loginType)) {
            // 自定义默认失败处理方式，json格式。
            response.setStatus(HttpStatus.INSUFFICIENT_STORAGE.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            // 调用默认失败的处理方式，返回页面。
            super.onAuthenticationFailure(request, response, exception);
        }

    }

}
