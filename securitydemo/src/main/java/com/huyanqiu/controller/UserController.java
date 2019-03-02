package com.huyanqiu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.huyanqiu.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户模块
 * @ClassName UserController
 * @author: huyanqiu
 * @since: 2019/2/12 18:03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户返回当前登录用户信息
     * 1、直接在方法的参数加上Authentication authentication1，自动注入当前登录用户信息
     * 2、通过SecurityContextHolder.getContext().getAuthentication()获取
     * @return
     */
    @RequestMapping("/me")
    public Object getCurrentUser(Authentication authentication1) {
        // 保存用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 登录用户userDetails信息
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
        return authentication;
    }

    /**
     * 创建用户
     * 注解@Valid用于校验字段是否为空等等，当没有BindingResult的时候，字段校验不合法不会进入到方法内，加上BindingResult则会进入到方法体中
     * @param user
     * @param result
     * @return
     */
    @PostMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "创建用户")
    public User create(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println(error);
            }

        }

        System.out.println("id:"+user.getId());
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("birthdate:"+user.getBirthdate());
        user.setId("1");
        return user;
    }

    /**
     * 更新用户
     * 注解@Valid用于校验字段是否为空等等，当没有BindingResult的时候，字段校验不合法不会进入到方法内，加上BindingResult则会进入到方法体中
     * @param user
     * @param result
     * @return
     */
    @PutMapping("/{id}")
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "更新用户")
    public User update(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println(error);
            }

        }

        System.out.println("id:"+user.getId());
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("birthdate:"+user.getBirthdate());
        return user;
    }

    /**
     * 删除用户信息
     * @param id 用户id
     */
    @DeleteMapping("/{id}")
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "删除用户信息")
    public void delete(@ApiParam(value = "用户ID") @PathVariable(name = "id") String id) {
        System.out.println("删除用户："+id);
        // 测试全局异常
        throw new RuntimeException("删除用户失败");
    }

    /**
     * 获取所有用户信息
     * @param username
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "获取所有用户信息")
    public List<User> findAll(@RequestParam(required = false) String username) {
        System.out.println("username:"+username);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    /**
     * 使用正则表达式限制id只能为数字
     * 原来： @RequestMapping("/user/{id}")
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "查询某个用户")
    public User queryUser(@PathVariable Long id) {
        System.out.println("id:"+id);
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123456");
        return user;
    }

}
