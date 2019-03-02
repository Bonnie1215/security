package com.huyanqiu.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * social配置类
 * @ClassName SocialConfig
 * @author: huyanqiu
 * @since: 2019/2/24 13:37
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    /**
     * 操作数据库，保存用户和QQ服务器用户之间的对应关系
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public SpringSocialConfigurer socialSecurityConfigurer() {
        return new SpringSocialConfigurer();
    }
}
