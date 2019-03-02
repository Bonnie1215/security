package com.huyanqiu.browser.anthentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huyanqiu.core.enume.LoginTypeEnum;
import com.huyanqiu.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功返回给前端的消息
 * 1、实现AuthenticationSuccessHandler接口
 * 2、继承
 * @ClassName MyAuthenticationSuccessHandler
 * @author: huyanqiu
 * @since: 2019/2/16 19:59
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 登录成功将自定义消息发送给前端
     * @param request
     * @param response
     * @param authentication 用户权限结果
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");

        LoginTypeEnum loginType = securityProperties.getBrowser().getLoginType();
        if (LoginTypeEnum.JSON.equals(loginType)) {
            // 返回json
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            // 调用默认成功处理方式(返回页面)
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
