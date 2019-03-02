package com.huyanqiu.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码异常
 * AuthenticationException是springsecurity身份认证抛出异常的一个基类
 * @ClassName ValidateCodeException
 * @author: huyanqiu
 * @since: 2019/2/21 19:19
 */
public class ValidateCodeException extends AuthenticationException{

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
