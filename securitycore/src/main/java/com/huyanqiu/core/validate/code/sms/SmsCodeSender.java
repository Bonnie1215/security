package com.huyanqiu.core.validate.code.sms;

/**
 * 发送短信验证码接口
 * @ClassName SmsCodeSender
 * @author: huyanqiu
 * @since: 2019/2/23 9:42
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     * @param mobile 手机号
     * @param code 验证码
     */
    public void send(String mobile, String code);

}
