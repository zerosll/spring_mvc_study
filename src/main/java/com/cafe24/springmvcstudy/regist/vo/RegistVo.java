package com.cafe24.springmvcstudy.regist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistVo {
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
}
