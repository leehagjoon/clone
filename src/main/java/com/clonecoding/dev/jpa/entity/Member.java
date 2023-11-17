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
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SNO")
    private Long memberSno;

    @Column(name = "MEMBER_ID", nullable = false)
    private String memberId;

    @Column(name = "MEMBER_PW", nullable = false)
    private String memberPw;

    @Column(name = "MEMBER_NAME", nullable = false)
    private String memberName;

    @Column(name = "BIRTH_DAY", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime birthDay;

    @Column(name = "NICK_NAME", nullable = false)
    private String nickName;

    @Column(name = "HPNO", nullable = false)
    private String hpNo;

    @Column(name = "MEMBER_STATUS_CD", nullable = false)
    private String memberStatusCd;

    @Column(name = "JOIN_DT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime joinDt;

    @Column(name = "GENDER_CD", nullable = false)
    private String genderCd;

    @Column(name = "PW_UPDT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime pwUpdtDt;

    @Column(name = "UPDT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updtDt;

    @Column(name = "MEMBR_AUTH")
    private String membrAuth;

}
