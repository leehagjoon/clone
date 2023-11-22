package com.clonecoding.dev.api.acnt.service;

import com.clonecoding.dev.api.acnt.model.MemberModel;
import com.clonecoding.dev.jpa.entity.Member;
import com.clonecoding.dev.jpa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
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
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional
    public void join(MemberModel memberModel) {

        String encryptedPw = bCryptPasswordEncoder.encode(memberModel.getMemberPw());
        Member member = Member.builder()
                .memberSno(memberModel.getMemberSno())
                .memberId(memberModel.getMemberId())
                .memberPw(encryptedPw)
                .memberName(memberModel.getMemberName())
                .birthDay(memberModel.getBirthDay())
                .nickName(memberModel.getNickName())
                .hpNo(memberModel.getHpNo())
                .memberStatusCd(memberModel.getMemberStatusCd())
                .joinDt(memberModel.getJoinDt())
                .genderCd(memberModel.getGenderCd())
                .build();
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByMemberId(member.getMemberId());

        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member members = memberRepository.findByMemberId(memberId);

        if(members == null){
            throw new UsernameNotFoundException(memberId);
        }
        return User.builder()
                .username(members.getMemberId())
                .password(members.getMemberPw())
                .roles(members.getMembrAuth().toString())
                .build();
    }
}
