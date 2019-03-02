package com.huyanqiu.core.validate.code.sms;

/**
 * 默认验证码发送器实现类
 * @ClassName DefaultSmsCodeSender
 * @author: huyanqiu
 * @since: 2019/2/23 9:43
 */
public class DefaultSmsCodeSender implements SmsCodeSender{

    /**
     * 默认发送短信验证码
     * @param mobile 手机号
     * @param code 验证码
     */
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }

}
