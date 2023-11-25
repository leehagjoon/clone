package com.clonecoding.dev.api.acnt.service;

import com.clonecoding.dev.jpa.entity.Member;
import com.clonecoding.dev.jpa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.clonecoding.dev.api.acnt.service
 * fileName       : MemberPrincipalDetailService
 * author         : hagjoon
 * date           : 2023-11-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-25        hagjoon       최초 생성
 */
@Service
@Slf4j
public class MemberPrincipalDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId);
        log.info("userName = {} ", memberId);
        log.info("member = {} ", member);
        if(member == null) throw new UsernameNotFoundException(memberId + "를 찾을 수 없습니다.");
        return new MemberPrincipalDetails(member);
    }
}
