package com.huyanqiu.core.properties;

/**
 * 图形验证码配置信息
 * @ClassName ImageCodeProperties
 * @author: huyanqiu
 * @since: 2019/2/21 20:03
 */
public class ImageCodeProperties extends SmsCodeProperties{

    /**
     * 验证码显示宽度
     */
    private int width = 67;
    /**
     * 验证码显示长度
     */
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
