package com.huyanqiu.core.config;

import com.huyanqiu.core.properties.SecurityProperties;
import com.huyanqiu.core.validate.code.ImageCodeGenerator;
import com.huyanqiu.core.validate.code.ValidateCodeGenerator;
import com.huyanqiu.core.validate.code.sms.DefaultSmsCodeSender;
import com.huyanqiu.core.validate.code.sms.SmsCodeGenerator;
import com.huyanqiu.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置类
 * @ClassName ValidateCodeBeanConfig
 * @author: huyanqiu
 * @since: 2019/2/21 20:37
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name="smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator() {
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
