package com.clonecoding.dev.api.acnt.model;

import com.clonecoding.dev.comm.RoleType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Builder
    public MemberModel(int memberSno, String memberId, String memberPw, String memberName, LocalDate birthDay,
                       String nickName, String hpNo,String genderCd) {
        this.memberSno = memberSno;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.birthDay = birthDay;
        this.nickName = nickName;
        this.hpNo = hpNo;
        this.memberStatusCd = "Y";
        this.joinDt = LocalDateTime.now();
        this.genderCd = genderCd;
    }
}
