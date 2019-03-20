package com.cafe24.springmvcstudy.regist.dto;

import com.cafe24.springmvcstudy.member.entity.Member;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberDto {

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    @Length(min = 5)
    private String password;
    private String confirmPassword;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .build();
    }
}