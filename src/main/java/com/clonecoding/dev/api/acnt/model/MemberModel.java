package com.clonecoding.dev.api.acnt.model;

import com.clonecoding.dev.comm.RoleType;
import com.clonecoding.dev.jpa.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.dev.api.acnt.model
 * fileName       : MemberModel
 * author         : hagjoon
 * date           : 2023-11-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-20        hagjoon       최초 생성
 */
@Data
@Builder
public class MemberModel {

    private int memberSno;

    private String memberId;

    private String memberPw;

    private String memberName;

    private LocalDate birthDay;

    private String nickName;

    private String hpNo;

    private String memberStatusCd;

    private LocalDateTime joinDt;

    private String genderCd;

    private RoleType memberAuth;

    /*
    DTO -> Entity
     */
    public Member toEntity(){
        Member member = Member.builder()
                .memberId(this.memberId)
                .memberPw(this.memberPw)
                .memberName(this.memberName)
                .birthDay(this.birthDay)
                .nickName(this.nickName)
                .hpNo(this.hpNo)
                .memberStatusCd(this.memberStatusCd)
                .joinDt(this.joinDt)
                .genderCd(this.genderCd)
                .memberAuth(this.memberAuth)
                .build();
        return member;
    }
}
