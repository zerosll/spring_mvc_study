package com.cafe24.springmvcstudy.regist.dto;

import com.cafe24.springmvcstudy.common.exception.WrongIdPasswordException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;
    private String address;

}