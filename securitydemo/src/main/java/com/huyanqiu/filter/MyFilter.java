package com.huyanqiu.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器
 * @ClassName MyFilter
 * @author: huyanqiu
 * @since: 2019/2/13 19:23
 */
//@Component
public class MyFilter implements Filter{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("MyFilter init......");
    }

    /**
     * 过滤器逻辑
     * @param resuest
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest resuest, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 开始时间
        long startTime = System.currentTimeMillis();
        chain.doFilter(resuest, response);
        // 结束时间
        long endTime = System.currentTimeMillis();
        logger.info("MyFilter花费时间："+(endTime-startTime));
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        logger.info("MyFilter destroy(......");
    }
}
