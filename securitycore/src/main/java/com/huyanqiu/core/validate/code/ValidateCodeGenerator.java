package com.huyanqiu.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器基类
 * @ClassName ValidateCodeGenerator
 * @author: huyanqiu
 * @since: 2019/2/21 20:30
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     * @return
     */
    public ValidateCode generator(ServletWebRequest request);

}
