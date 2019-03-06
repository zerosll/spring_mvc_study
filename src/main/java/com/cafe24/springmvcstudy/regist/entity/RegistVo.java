package com.cafe24.springmvcstudy.regist.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RegistVo {

    @NotNull
    @Email
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
}
