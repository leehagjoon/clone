package com.clonecoding.dev.jpa.entity;

import com.clonecoding.dev.comm.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
public class Member implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
      collection.add(new SimpleGrantedAuthority(String.valueOf(memberAuth)));
        return collection;
    }

    @Override
    public String getPassword() {
        return this.memberPw;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
