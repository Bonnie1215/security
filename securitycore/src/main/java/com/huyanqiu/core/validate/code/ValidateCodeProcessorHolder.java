package com.huyanqiu.core.validate.code;

import com.huyanqiu.core.enume.ValidateCodeTypeEnum;
import com.huyanqiu.core.validate.code.template.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 系统中校验码处理器
 * @ClassName ValidateCodeProcessorHolder
 * @author: huyanqiu
 * @since: 2019/2/23 16:38
 */
@Component
public class ValidateCodeProcessorHolder {

    /**
     * 自动收集实现ValidateCodeProcessor接口的类
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 获取容器中对应的验证码处理器
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcess(ValidateCodeTypeEnum type) {
        return findValidateCodeProcess(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcess(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor process = validateCodeProcessors.get(name);
        if (process==null) {
            throw new ValidateCodeException("验证码处理器" + name +"不存在");
        }
        return process;
    }

}
