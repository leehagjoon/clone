package com.clonecoding.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * packageName    : com.clonecoding.dev.config
 * fileName       : SecurityConfig
 * author         : hagjoon
 * date           : 2023-11-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-20        hagjoon       최초 생성
 */
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다.
public class SecurityConfig {



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
                security
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/","/api/acnt/**","/api/cns/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/api/acnt/login") // 로그인페이지 URL
                        .defaultSuccessUrl("/") // 로그인 성공시 이동할 페이지
                        .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/acnt/logout")) // 로그아웃 URL
                        .logoutSuccessUrl("/"); // 로그아웃 성공시 URL
                return security.build();
    }
}
