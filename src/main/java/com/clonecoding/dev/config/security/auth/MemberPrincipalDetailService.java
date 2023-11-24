package com.clonecoding.dev.config.security.auth;

import com.clonecoding.dev.jpa.entity.Member;
import com.clonecoding.dev.jpa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.clonecoding.dev.api.acnt.auth
 * fileName       : MemberPrincipalDetailService
 * author         : hagjoon
 * date           : 2023-11-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-24        hagjoon       최초 생성
 */
// UserDetailsService를 구현한 클래스
    // 스프링 시큐리티가 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함
@Service
@Slf4j
public class MemberPrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberPrincipalDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 넘겨받은 id로 DB에서 회원 정보를 찾음
        Member member = memberRepository.findByMemberId(username);
        log.info("username : {} ", username);
        log.info("member : {} ", member);
        // 없을 경우 에러발생
        if(member == null) {
            throw new UsernameNotFoundException(username + "를 찾을 수 없습니다.");
        }
        if(!"Y".equals(member.getMemberStatusCd())){
            throw new UsernameNotFoundException("사용할 수 없는 계정입니다.");
        }
        if(!"N".equals(member.getMemberStatusCd())){
            throw new UsernameNotFoundException("삭제된 계정입니다.");
        }
        //MemberPrincipalDetails 에 Member 객체를 넘겨줌
        return new MemberPrincipalDetails(member);
    }
}
