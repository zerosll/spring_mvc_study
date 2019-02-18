package com.cafe24.springmvcstudy.regist.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistVo {
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
}
