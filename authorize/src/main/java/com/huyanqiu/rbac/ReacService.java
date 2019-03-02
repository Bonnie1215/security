package com.huyanqiu.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * RBAC权限自定义权限校验接口
 * @ClassName ReacService
 * @author: huyanqiu
 * @since: 2019/2/26 19:44
 */
public interface ReacService {

    /**
     * 是否有权限
     * @param request
     * @param authentication
     * @return
     */
    Boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
