package com.huyanqiu.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @ClassName MyInterceptor
 * @author: huyanqiu
 * @since: 2019/2/13 19:46
 */
@Component
public class MyInterceptor implements HandlerInterceptor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 控制器方法被执行之前执行
     * @param request
     * @param response
     * @param handle
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handle) throws Exception {
        logger.info("MyInterceptor preHandle......");
        HandlerMethod handleMethod = (HandlerMethod) handle;
        // 调用控制器的方法名称
        String methodName = handleMethod.getMethod().getName();
        String controllerName = handleMethod.getBean().getClass().getName();
        logger.info("preHandle方法信息："+controllerName+":"+methodName);

        // 方法执行的开始时间
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * 控制器的方法处理之后执行，（如果控制器中的方法抛出异常，该方法不会被执行。）
     * @param request
     * @param response
     * @param handle
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handle, ModelAndView modelAndView)
            throws Exception {
        logger.info("MyInterceptor postHandle......");
        // 获得方法执行的开始时间
        Long startTime = (Long)request.getAttribute("startTime");
        logger.info("MyInterceptor方法执行花费时间："+(System.currentTimeMillis() - startTime));
    }

    /**
     * 控制器方法执行之后执行，（不管控制中的方法是否抛出异常，该方法都会被执行。）
     * @param request
     * @param response
     * @param handle
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handle, Exception ex) throws Exception {
       logger.info("MyInterceptor afterCompletion......");
        // 获得方法执行的开始时间
        Long startTime = (Long)request.getAttribute("startTime");
        logger.info("MyInterceptor方法执行花费时间："+(System.currentTimeMillis() - startTime));
        logger.info("ex异常信息："+ex);
    }
}
