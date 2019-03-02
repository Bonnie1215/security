package com.huyanqiu.test;

import com.huyanqiu.core.validate.code.ImageCode;
import com.huyanqiu.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

//@Component("imageCodeGenerator")
public class MyImageCodeGenerator implements ValidateCodeGenerator{
    @Override
    public ImageCode generator(ServletWebRequest request) {
        System.out.println("覆盖core中默认的图形验证码");
        return null;
    }
}
