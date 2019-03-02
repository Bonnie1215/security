package com.huyanqiu.core.validate.code.sms;

import com.huyanqiu.core.properties.SecurityProperties;
import com.huyanqiu.core.validate.code.ImageCode;
import com.huyanqiu.core.validate.code.ValidateCode;
import com.huyanqiu.core.validate.code.ValidateCodeGenerator;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 短信验证码
 * @ClassName ImageCodeGenerator
 * @author: huyanqiu
 * @since: 2019/2/21 20:33
 */
public class SmsCodeGenerator implements ValidateCodeGenerator{

    /**
     * 自定义配置类
     */
    private SecurityProperties securityProperties;

    /**
     * 生成图片验证码
     * @param request
     * @return
     */
    @Override
    public ValidateCode generator(ServletWebRequest request) {
        // 生成4位随机数
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExprieIn());
}


    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

}
