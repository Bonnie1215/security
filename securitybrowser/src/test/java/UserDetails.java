import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * UserDetails类封装了springsecurity登录需要的所有信息
 * @ClassName UserDetails
 * @author: huyanqiu
 * @since: 2019/2/16 15:46
 */
public interface UserDetails extends Serializable {

    /**
     * 权限信息
     * @return
     */
    Collection<? extends GrantedAuthority> getAuthorities();

    /**
     * 密码
     * @return
     */
    String getPassword();

    /**
     * 用户名
     * @return
     */
    String getUsername();

    /**
     * 账户是否过期， true：未过期   false：已过期
     * @return
     */
    boolean isAccountNonExpired();

    /**
     * 账户是否被锁定(账户是否被冻结)： true：未冻结  false：已冻结
     * @return
     */
    boolean isAccountNonLocked();

    /**
     * 密码是否过期，true：未过期  false：已过期
     * @return
     */
    boolean isCredentialsNonExpired();

    /**
     * 账户是否可用(是否被删除)： true：未删除  false：已删除
     * @return
     */
    boolean isEnabled();
}
