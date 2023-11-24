package com.clonecoding.dev.api.acnt.service;

import com.clonecoding.dev.api.acnt.model.MemberModel;
import com.clonecoding.dev.jpa.entity.Member;
import com.clonecoding.dev.jpa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * packageName    : com.clonecoding.dev.api.acnt.service
 * fileName       : MemberService
 * author         : hagjoon
 * date           : 2023-11-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-20        hagjoon       최초 생성
 */
@Service
@Slf4j
public class MemberService implements UserDetailsService{


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional
    public void join(MemberModel memberModel) {

        memberModel.setMemberPw(bCryptPasswordEncoder.encode(memberModel.getMemberPw()));
        memberRepository.save(memberModel.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username);
        return toUserDetail(member);
    }

    private UserDetails toUserDetail(Member member){
        return User.builder()
                .username(member.getMemberId())
                .password(member.getMemberPw())
                .authorities(new SimpleGrantedAuthority(member.getMemberAuth().toString()))
                .build();
    }

}
