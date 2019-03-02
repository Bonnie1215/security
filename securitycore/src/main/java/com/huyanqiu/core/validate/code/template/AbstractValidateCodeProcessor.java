package com.huyanqiu.core.validate.code.template;

import com.huyanqiu.core.enume.ValidateCodeTypeEnum;
import com.huyanqiu.core.validate.code.ImageCode;
import com.huyanqiu.core.validate.code.ValidateCode;
import com.huyanqiu.core.validate.code.ValidateCodeException;
import com.huyanqiu.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 创建验证码的抽象实现
 * @ClassName AbstractValidateCodeProcess
 * @author: huyanqiu
 * @since: 2019/2/23 10:43
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * session处理器
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有实现ValidateCodeGenerator接口的实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 主体业务流程
     * @param request
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        // 1、生成
        T validateCode = generator(request);
        // 2、保存
        save(request, validateCode);
        // 3、发送
        send(request, validateCode);
    }

    /**
     * 生成校验码
     * @param request
     * @return
     */
    public T generator(ServletWebRequest request) {
        String type = getProcessType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (T)validateCodeGenerator.generator(request);
    }

    /**
     * 保存验证码
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, T validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessType(request).toUpperCase(),validateCode);
    }

    /**
     * 校验验证码逻辑
     * @param request
     */
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeTypeEnum processorType = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);
        // 获取存放在session中验证码信息
        T codeInSession = (T)sessionStrategy.getAttribute(request, sessionKey);
        // 登录请求中验证码
        String codeInRequet;
        try {
            codeInRequet = ServletRequestUtils.getStringParameter(request.getRequest(), processorType.getParamNameOnValidate());
        } catch (Exception e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequet)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.idExpried()) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequet)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        // 移除session中的验证码
        sessionStrategy.removeAttribute(request, processorType.getParamNameOnValidate());

    }

    /**
     * 发送验证码，由子类实现
     * @param request
     * @param validateCode
     */
    public abstract void send(ServletWebRequest request, T validateCode) throws Exception;

    /**
     * 根据请求URL获取验证码的类型
     * @param request
     * @return
     */
    private String getProcessType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    /**
     * 根据请求的url获取校验码的类型
     * @param request
     * @return
     */
    private ValidateCodeTypeEnum getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
        return ValidateCodeTypeEnum.valueOf(type.toUpperCase());
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

}
