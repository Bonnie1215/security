package com.huyanqiu.core.validate.code;

import com.huyanqiu.core.enume.ValidateCodeTypeEnum;
import com.huyanqiu.core.properties.SecurityConstants;
import com.huyanqiu.core.properties.SecurityProperties;
import com.huyanqiu.core.validate.code.template.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图片验证码以及手机验证码校验合并到一个Filter
 * @ClassName SmsAndImageValidateCodeFilterextends
 * @author: huyanqiu
 * @since: 2019/2/23 16:28
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 封装了处理session信息
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 登录异常处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统中校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 存放所有需要过滤验证码的URl
     */
    private Map<String, ValidateCodeTypeEnum> urlMap = new HashMap<>();
    /**
     * 自定义配置类
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * spring验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化要拦截的URL配置信息
     *
     * @throws ServletException
     */
    @Override public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 将图片验证码需要拦截的URL添加进来
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeTypeEnum.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeTypeEnum.IMAGE);

        // 将手机验证码需要拦截的URL添加进来
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeTypeEnum.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeTypeEnum.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 获取当前请求URI是否需要经过验证码过滤器
        ValidateCodeTypeEnum type = getValidateCodeType(request);

        // 只有登录的请求过滤器才会生效
        if (type!=null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                // 校验验证码逻辑
                 validateCodeProcessorHolder.findValidateCodeProcess(type)
                        .validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 放行
        filterChain.doFilter(request, response);

    }

    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放到map中
     * @param urlString 拦截URL字符串(英文逗号分隔)
     * @param type 图片验证码类型、手机验证码类型
     */
    private void addUrlToMap(String urlString, ValidateCodeTypeEnum type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     * @param request
     * @return
     */
    private ValidateCodeTypeEnum getValidateCodeType(HttpServletRequest request) {
        ValidateCodeTypeEnum result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

}

