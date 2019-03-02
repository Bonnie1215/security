package com.huyanqiu.core.validate.code.template;

import com.huyanqiu.core.properties.SecurityConstants;
import com.huyanqiu.core.validate.code.ValidateCode;
import com.huyanqiu.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证
 * @ClassName SmsValidateCodeProcessor
 * @author: huyanqiu
 * @since: 2019/2/23 11:05
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

    /**
     * 短信验证码的发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
