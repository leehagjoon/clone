package com.clonecoding.dev.config;

import com.clonecoding.dev.api.acnt.provider.MemberAuthenticatorProvider;
import com.clonecoding.dev.api.acnt.service.MemberPrincipalDetailService;
import com.clonecoding.dev.api.acnt.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {


    @Autowired
    MemberAuthenticatorProvider memberAuthenticatorProvider;

    @Autowired
    MemberPrincipalDetailService memberPrincipalDetailService;

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(memberAuthenticatorProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
//      security
//              .authorizeRequests()
//              .antMatchers("/")
//              .permitAll();
//      security
//              .formLogin()
//              .loginPage("/api/acnt/login")
//              .loginProcessingUrl("/api/acnt/loginProc")
//              .defaultSuccessUrl("/")
//              .failureUrl("/")
//              .and()
//              .logout()
//              .and()
//              .csrf().disable();

            security.cors(cors -> cors.disable())
                    .csrf(csrf -> csrf.disable());

            security.authorizeRequests(
                    authorizeRequests -> authorizeRequests
                            .antMatchers("/api/cns/noticewrite", "/api/cns/noticeupdate").authenticated()
                            .anyRequest().permitAll()
            );

            security.formLogin(
                    formLogin -> formLogin.loginPage("/api/acnt/login")
                            .loginProcessingUrl("/api/acnt/loginProc")
                            .defaultSuccessUrl("/")
                            .failureUrl("/")
            )
                    .logout(
                            logout -> logout.logoutUrl("/api/acnt/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true) //HTTP 세션 무효화
                                    .deleteCookies("JSESSIONID") // 쿠키 삭제
                    );


              return security.build();
    }

}
