package com.clonecoding.dev.api.acnt.service;

import com.clonecoding.dev.api.acnt.model.MemberModel;
import com.clonecoding.dev.comm.RoleType;
import com.clonecoding.dev.jpa.entity.Member;
import com.clonecoding.dev.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberModel memberModel) {
        Member member = Member.builder()
                .memberSno(memberModel.getMemberSno())
                .memberId(memberModel.getMemberId())
                .memberPw(memberModel.getMemberPw())
                .memberName(memberModel.getMemberName())
                .birthDay(memberModel.getBirthDay())
                .nickName(memberModel.getNickName())
                .hpNo(memberModel.getHpNo())
                .memberStatusCd(memberModel.getMemberStatusCd())
                .joinDt(memberModel.getJoinDt())
                .genderCd(memberModel.getGenderCd())
                .membrAuth(memberModel.getMemberAuth())
                .build();
        memberRepository.save(member);
    }
}
