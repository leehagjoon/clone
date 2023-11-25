package com.clonecoding.dev.api.acnt.provider;

import com.clonecoding.dev.api.acnt.service.MemberPrincipalDetailService;
import com.clonecoding.dev.api.acnt.service.MemberPrincipalDetails;
import com.clonecoding.dev.jpa.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.clonecoding.dev.api.acnt.provider
 * fileName       : MemberAuthenticatorProvider
 * author         : hagjoon
 * date           : 2023-11-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-25        hagjoon       최초 생성
 */
@Component
public class MemberAuthenticatorProvider implements AuthenticationProvider {

    @Autowired
    private MemberPrincipalDetailService memberPrincipalDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberPrincipalDetails memberPrincipalDetails = (MemberPrincipalDetails) memberPrincipalDetailService.loadUserByUsername(username);

        String dbPassword = memberPrincipalDetails.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(!passwordEncoder.matches(password,dbPassword)){
            throw new BadCredentialsException("[사용자] 아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        Member member = memberPrincipalDetails.getMember();
        if(member == null){
            throw new BadCredentialsException("[사용자] 사용할 수 없는 계정입니다.");
        }

        return new UsernamePasswordAuthenticationToken(memberPrincipalDetails,null,memberPrincipalDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
