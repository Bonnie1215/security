package com.huyanqiu.core.validate.code.template;


import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * @ClassName ValidateCodeProcess
 * @author: huyanqiu
 * @since: 2019/2/23 10:39
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    public String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    public void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码逻辑
     * @param request
     */
    public void validate(ServletWebRequest request);

}
