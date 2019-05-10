package com.cafe24.springmvcstudy.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder ;

    // 시큐리티의 내용 외 파라미터를 추가하고 싶을 때, 아래 사용
    // 제약조건: Controller 에서 Auth를 점검할 때, UserCustom 으로 받아야 함.
    // 예) (변경 전) @AuthenticationPrincipal User user => (변경 후) @AuthenticationPrincipal UserCustom user

    public Account createAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRegDateTime(new Date());
        account.setEmail("tldn23@gmail.com");
        account.setActive(1);
        Role role = createRole("ROLE_ADMIN");
        account.setRoleId(role.getId());
        return accountRepository.save(account);
    }

    private Role createRole(String roleName){
        Role role = new Role();
        role.setRoleName(roleName);
        return roleRepository.save(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new CustomUser(account.getUsername(),
                            account.getPassword(),
                            enabled,
                            accountNonExpired,
                            credentialsNonExpired,
                            accountNonLocked,
                            authorities(account.getRoleId()),
                            account.getEmail(),
                            account.getPhoneNumber()
                            );
        //return new User(account.getUsername(), account.getPassword(), authorities(account.getRoleId()));
    }

/*    private Collection<? extends GrantedAuthority> authorities(Set<Role> roles) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role authority : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getRoleName());
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }*/
   private Collection<? extends GrantedAuthority> authorities(Long roleId) {
       Optional<Role> byId = roleRepository.findById(roleId);
       Role role = byId.orElseThrow(() -> new NullPointerException(roleId.toString()));
        return Arrays.asList(new SimpleGrantedAuthority(role.getRoleName()));
    }
}