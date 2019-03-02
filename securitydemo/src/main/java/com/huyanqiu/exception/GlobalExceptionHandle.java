package com.huyanqiu.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * springboot异常处理BasicErrorController，针对网页和接口访问返回的结果不同。
 * @ClassName GlobalExceptionHandle
 * @author: huyanqiu
 * @since: 2019/2/13 19:09
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> runtimeException(RuntimeException e) {
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("code", -1);
        resultMap.put("message", e.getMessage());
        return resultMap;
    }

}
