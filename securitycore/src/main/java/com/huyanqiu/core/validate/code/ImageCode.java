package com.huyanqiu.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码
 * @ClassName ImageCode
 * @author: huyanqiu
 * @since: 2019/2/17 10:17
 */
public class ImageCode extends ValidateCode{

    /**
     * 验证码图片
     */
    private BufferedImage image;

    /**
     * 构造方法
     * @param image  验证码图片
     * @param code 验证码内容
     * @param seconds 设置多少秒过期
     */
    public ImageCode(BufferedImage image, String code, int seconds) {
        super(code, seconds);
        this.image = image;
    }

    /**
     * 构造方法
     * @param image
     * @param code
     * @param expireTime
     */
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
