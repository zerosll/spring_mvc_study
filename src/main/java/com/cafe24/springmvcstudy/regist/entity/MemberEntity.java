package com.cafe24.springmvcstudy.regist.entity;

import com.cafe24.springmvcstudy.common.exception.WrongIdPasswordException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
//@Entity
public class MemberEntity {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;
    //VO
    private Address address;


    //DDD
    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword))
            throw new WrongIdPasswordException();
        this.password = newPassword;
    }
}

class Address {
    private String addr1;
    private String addr2;
}