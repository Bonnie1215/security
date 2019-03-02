package com.huyanqiu.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证过滤器
 * @ClassName SmsCodeAuthenticationFilter
 * @author: huyanqiu
 * @since: 2019/2/23 14:57
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;

    /**
     * 当前过滤器是不是只处理post请求
     */
    private boolean postOnly = true;

    /**
     * 构造方法
     */
    public SmsCodeAuthenticationFilter() {
        // 当前过滤器匹配/authentication/mobile请求
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }

    /**
     * 短信认证流程
     * @param request 请求
     * @param response 响应
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        // 当前请求是否是post请求
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        // 获取请求参数中的手机号
        String mobile = obtainMobile(request);

        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        // 将请求手机号封装到SmsCodeAuthenticationToken对象中
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

        // 将请求的信息也设置到SmsCodeAuthenticationToken中
        setDetails(request, authRequest);

        // 把SmsCodeAuthenticationToken放到tAuthenticationManager中
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取请求参数中的手机号
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    /**
     * 把请求的详情设置到details，如IP等信息
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request,
            SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 设置手机号
     * @param mobileParameter
     */
    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    /**
     * 设置是否只处理post请求
     * @param postOnly
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     * 获取请求参数中手机号
     * @return
     */
    public final String getMobileParameter() {
        return mobileParameter;
    }

}
