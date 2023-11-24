package com.clonecoding.dev.jpa.entity;

import com.clonecoding.dev.comm.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.dev.jpa.entity
 * fileName       : Member
 * author         : hagjoon
 * date           : 2023-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-17        hagjoon       최초 생성
 */
@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SNO")
    private int memberSno;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_PW")
    private String memberPw;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "BIRTH_DAY")
    private LocalDate birthDay;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "HPNO")
    private String hpNo;

    @Column(name = "MEMBER_STATUS_CD")
    private String memberStatusCd;

    @Column(name = "JOIN_DT")
    private LocalDateTime joinDt;

    @Column(name = "GENDER_CD")
    private String genderCd;

    @Column(name = "PW_UPDT_DT")
    private LocalDateTime pwUpdtDt;

    @Column(name = "UPDT_DT")
    private LocalDateTime updtDt;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBR_AUTH")
    private RoleType memberAuth;

    @Builder
    public Member(int memberSno, String memberId, String memberPw, String memberName, LocalDate birthDay,
                  String nickName, String hpNo, String memberStatusCd, LocalDateTime joinDt, String genderCd,
                  LocalDateTime pwUpdtDt, LocalDateTime updtDt, RoleType memberAuth) {
        this.memberSno = memberSno;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.birthDay = birthDay;
        this.nickName = nickName;
        this.hpNo = hpNo;
        this.memberStatusCd = memberStatusCd;
        this.joinDt = joinDt;
        this.genderCd = genderCd;
        this.pwUpdtDt = pwUpdtDt;
        this.updtDt = updtDt;
        this.memberAuth = memberAuth;
    }
}
