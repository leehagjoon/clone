package com.clonecoding.dev.config.security.provider;

import com.clonecoding.dev.config.security.auth.MemberPrincipalDetailService;
import com.clonecoding.dev.config.security.auth.MemberPrincipalDetails;
import com.clonecoding.dev.jpa.entity.Member;
import lombok.extern.slf4j.Slf4j;
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
 * fileName       : MemberAuthenticationProvider
 * author         : hagjoon
 * date           : 2023-11-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-24        hagjoon       최초 생성
 */
@Component
@Slf4j
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final MemberPrincipalDetailService memberPrincipalDetailService;

    public MemberAuthenticationProvider(MemberPrincipalDetailService memberPrincipalDetailService) {
        this.memberPrincipalDetailService = memberPrincipalDetailService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // 사용자가 입력한 id
        String password = (String)authentication.getCredentials(); // 사용자가 입력한 password

        //생성해둔 MemberPrincipalDetailSerivce 에서 loadUserByUsername메소드를 호출하여 사용자 정보를 가져온다.
        MemberPrincipalDetails memberPrincipalDetails = (MemberPrincipalDetails) memberPrincipalDetailService.loadUserByUsername(username);
        //=================================================비밀번호 비교 =====================================================================
        //사용자가 입력한 password 와 DB에 저장된 password를 비교

        //DB에 저장된 password
        String dbPassword = memberPrincipalDetails.getPassword();
        //암호화 방식(BcryptPasswordEncoder)를 사용하여 비밀번호를 비교
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(!passwordEncoder.matches(password,dbPassword)){
            log.info("[사용자] 비밀번호가 일치하지 않음 ");
            throw new BadCredentialsException("[사용자] 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        //====================================================================================================================================

        //사용자가 입력한 id와 password 가 일치하면 인증 성공
        //인증이 성공하면 MemberPrincipalDetails 객체를 반환
        Member member = memberPrincipalDetails.getMember();
        if(member == null || "N".equals(member.getMemberStatusCd())){
            log.info("[사용자] 사용할 수 없는 계정");
            throw new BadCredentialsException("[사용자] 사용할 수 없는 계정입니다.");
        }

        //인증이 성공하면 UsernamePasswordAuthenticationToken 객체 반환
        //해당 객체는 Authentication 객체로 추후 인증이 끝나고 SecurityContexHolder.getContext()에 저장
        return new UsernamePasswordAuthenticationToken(memberPrincipalDetails,null,memberPrincipalDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
