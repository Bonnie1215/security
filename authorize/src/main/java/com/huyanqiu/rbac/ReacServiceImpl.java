package com.huyanqiu.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * RBAC权限自定义权限校验实现
 * @ClassName ReacServiceImpl
 * @author: huyanqiu
 * @since: 2019/2/26 19:46
 */
@Component("reacServiceImpl")
public class ReacServiceImpl implements ReacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 是否有权限
     * @param request
     * @param authentication
     * @return
     */
    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        System.out.println("自定义权限认证表达式");
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            // 获取用户名
            String username = ((UserDetails) principal).getUsername();
            // TODO 读取用户所用用权限的所有URL
            Set<String> urlSet = new HashSet<>();
            for (String url : urlSet) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
