package com.clonecoding.dev.config.security;

import com.clonecoding.dev.config.security.auth.MemberPrincipalDetailService;
import com.clonecoding.dev.config.security.config.MemberAuthFailureHandler;
import com.clonecoding.dev.config.security.provider.MemberAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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


    // 생성해둔 MemberAuthenticatiorProvider를 주입
    // 해당 클래스로 MemberPrincipalDetailsService 내부 로직을 수행하며 인증 처리도 같이 진행
    @Autowired
    MemberAuthenticationProvider memberAuthenticationProvider;

    //로그인 기억하기 사용을 위해 MemberAuthenticationProvider 내부 MemberPrincipalDetailsService 선언
    @Autowired
    MemberPrincipalDetailService memberPrincipalDetailService;



    // in memory 방식으로 인증 처리를 진행 하기 위해 기존에 Override 하여 구현했지만
    // 버전 변경으로 AuthenticationManagerBuilder를 직접생성하여 AuthenticationManager를 생성해야함
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(memberAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security.csrf().disable();
        security.authorizeRequests(authorize -> {
           try{
               authorize
                       .antMatchers("/","/api/cns/**","/auth/**")
                       .permitAll() //해당 경로는 인증 없이 접근 가능
                       .and()
                       .formLogin()
                       .loginPage("/api/acnt/login") // 로그인 페이지 설정
                       .loginProcessingUrl("/api/acnt/login") // 로그인 처리 URL 설정
                       .defaultSuccessUrl("/api/cns/notice") // 로그인 성공 후 이동할 페이지
                       .failureHandler(new MemberAuthFailureHandler())// 로그인 실패 후 처리할 핸들러
                       .permitAll()
                       .and()
                       .logout()
                       .logoutUrl("/api/acnt/logout")// 로그아웃 처리 URL 설정
                       .logoutSuccessUrl("/home") // 로그아웃 성공 후 이동할 페이지
                       .deleteCookies("JSESSIONID"); // 로그아웃 후 쿠키 삭제
           }catch (Exception e){
               throw new RuntimeException(e);
           }
        });
        security.rememberMe()
                .key("rock") // 인증 토큰 생성 시 사용할 키
                .tokenValiditySeconds(60 * 60 * 24 * 7) // 인증 토큰 유효 시간(초)
                .userDetailsService(memberPrincipalDetailService)
                .rememberMeParameter("remember-me"); // 로그인 페이지에서 사용할 파라미터 이름
        return security.build();
    }
}
