package com.huyanqiu.core.config;

import com.huyanqiu.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 使SecurityProperties配置类生效
 * @ClassName SecurityCoreConfig
 * @author: huyanqiu
 * @since: 2019/2/16 19:38
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityCoreConfig {
}
