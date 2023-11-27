package com.clonecoding.dev.api.acnt.model;

import com.clonecoding.dev.comm.RoleType;
import com.clonecoding.dev.jpa.entity.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

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
public class MemberModel{

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

    private String memberAuth;

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
                .memberStatusCd("Y")
                .joinDt(LocalDateTime.now())
                .genderCd(this.genderCd)
                .memberAuth(RoleType.valueOf(this.memberAuth))
                .build();
        return member;
    }


}
