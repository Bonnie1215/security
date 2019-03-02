package com.huyanqiu.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class User {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    @ApiModelProperty(value = "用户ID")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
    // 日期类型接收时间戳
    private Date birthdate;

    @JsonView({UserSimpleView.class})
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @JsonView({UserSimpleView.class})
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView({UserSimpleView.class})
    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView({UserDetailView.class})
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
