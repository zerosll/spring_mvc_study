package com.cafe24.springmvcstudy.common.config;

import com.cafe24.springmvcstudy.common.auth.AuthFailureHandler;
import com.cafe24.springmvcstudy.common.auth.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthFailureHandler authFailureHandler;

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                //.antMatchers("/").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers("/post/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .successHandler(authSuccessHandler)
                //.failureUrl("/login")
                .failureHandler(authFailureHandler)
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
            .csrf().disable()
            .httpBasic();

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/bower_components/**", "/dist/**", "/plugins/**", "/error/**", "/api/**");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

/*
authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/auth").failureUrl("/auth?error=true")
                .defaultSuccessUrl("/admin/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
 */