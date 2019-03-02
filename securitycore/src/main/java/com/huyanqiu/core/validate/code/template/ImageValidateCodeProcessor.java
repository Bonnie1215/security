package com.huyanqiu.core.validate.code.template;

import com.huyanqiu.core.validate.code.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 * @ClassName ImageValidateCodeProcessor
 * @author: huyanqiu
 * @since: 2019/2/23 11:14
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode>{

    /**
     * 发送图片验证码
     * @param request
     * @param imageCode
     * @throws Exception
     */
    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        // 输出到响应中
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}
