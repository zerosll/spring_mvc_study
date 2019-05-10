package com.cafe24.springmvcstudy.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Security 에서 사용되는 User 에서 파라미터를 추가함.
 */
@Getter
@Setter
@ToString
public class CustomUser extends User {

    // 유저의 정보를 더 추가하고 싶다면 이곳과, 아래의 생성자 파라미터를 조절해야 한다.
    private String email;
    private String phoneNumber;

    public CustomUser(String username,
                      String password,
                      boolean enabled,
                      boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities,
                      String email,
                      String phoneNumber) {
        super(username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
