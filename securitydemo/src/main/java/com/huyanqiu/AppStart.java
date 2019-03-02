package com.huyanqiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * EnableSwagger2 开启swagger生成文档
 * @ClassName AppStart
 * @author: huyanqiu
 * @since: 2019/2/13 20:37
 */
@SpringBootApplication
@EnableSwagger2
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }

}
