package com.clonecoding.dev.jpa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Long memberSno;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_PW")
    private String memberPw;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "BIRTH_DAY")
    private LocalDateTime birthDay;

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

    @Column(name = "MEMBR_AUTH")
    private String membrAuth;

    @Builder
    public Member(Long memberSno, String memberId, String memberPw, String memberName, LocalDateTime birthDay,
                  String nickName, String hpNo, String memberStatusCd, LocalDateTime joinDt, String genderCd,
                  LocalDateTime pwUpdtDt, LocalDateTime updtDt, String membrAuth) {
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
        this.membrAuth = membrAuth;
    }
}
