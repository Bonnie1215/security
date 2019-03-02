package com.huyanqiu.core.validate.code;

import java.time.LocalDateTime;

/**
 * 验证码基类
 * @ClassName ValidateCode
 * @author: huyanqiu
 * @since: 2019/2/23 9:34
 */
public class ValidateCode {

    /**
     * 验证码内容
     */
    private String code;
    /**
     * 过期时间点
     */
    private LocalDateTime expireTime;

    /**
     * 构造方法
     * @param code 验证码内容
     * @param seconds 设置多少秒过期
     */
    public ValidateCode(String code, int seconds) {
        this.code = code;
        // 过期时间 = 当前时间+失效时长
        this.expireTime = LocalDateTime.now().plusSeconds(seconds);
    }

    /**
     * 构造方法
     * @param code
     * @param expireTime
     */
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode() {
    }

    /**
     * 校验码是否过期
     * @return
     */
    public boolean idExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
