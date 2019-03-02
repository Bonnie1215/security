package com.huyanqiu.core.validate.code;

import com.huyanqiu.core.validate.code.template.ValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 获取验证码的controller
 * @ClassName ValidateCodeController
 * @author: huyanqiu
 * @since: 2019/2/17 10:23
 */
@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 收集实现ValidateCodeGenerator接口的类
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 获取图片验证码
     * @param request
     * @param response
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validateCodeProcessors.get(type+"ValidateCodeProcessor").create(new ServletWebRequest(request, response));
    }

}
