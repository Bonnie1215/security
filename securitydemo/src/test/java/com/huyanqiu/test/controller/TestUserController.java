package com.huyanqiu.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * 测试usercontroller类
 * @ClassName TestUserController
 * @author: huyanqiu
 * @since: 2019/2/12 18:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserController {

    @Autowired
    private WebApplicationContext wac;

    public MockMvc mockMvc;

    @Before
    public void init() {
        // 伪造一个mvc环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 发送一个http查询请求
     */
    @Test
    public void whenQuerySuccess() throws Exception{
        // 伪造一个http请求
       mockMvc.perform(MockMvcRequestBuilders.get("/user")
                              // 传递参数username=bonnie
                             .param("username", "bonnie")
                             // 使用utf-8的json格式类型
                             .contentType(MediaType.APPLICATION_JSON_UTF8))
                            // 返回结果状态码是200
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            // 返回结果的长度为3
                            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    /**
     * 测试查询请求
     */
    @Test
    public void whenGetInfoSuccess() throws Exception{
        // 伪造一个http请求
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                // 使用utf-8的json格式类型
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 返回结果状态码是200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 返回对象的username属性的值为tom
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                // 将返回结果按照string字符串输出
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 测试查询请求失败
     */
    @Test
    public void whenGetInfoFail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                // 使用utf-8的json格式类型
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 返回结果状态码是200
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * 测试创建用户请求
     */
    @Test
    public void whenCreateOnSuccess() throws Exception{
        long time = new Date().getTime();
        System.out.println(time);
        String content = "{\"username\":\"tom\",\"password\":null,\"birthdate\":"+time+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                // 新增user字符串
                .content(content)
                // 使用utf-8的json格式类型
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 返回结果状态码是200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 返回对象的id属性的值为1
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1")).andReturn()
                .getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 测试更新用户请求
     */
    @Test
    public void whenUpdateInSuccess() throws Exception{
        long time = new Date().getTime();
        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthdate\":"+time+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                // 新增user字符串
                .content(content)
                // 使用utf-8的json格式类型
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 返回结果状态码是200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 返回对象的id属性的值为1
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1")).andReturn()
                .getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 测试删除用户请求
     */
    @Test
    public void whenDeleteOnSuccess() throws Exception{
        long time = new Date().getTime();
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                // 使用utf-8的json格式类型
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 返回结果状态码是200
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
