package com.clonecoding.dev.config.security.auth;

import com.clonecoding.dev.jpa.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * packageName    : com.clonecoding.dev.api.acnt.auth
 * fileName       : MemberPrincipalDetails
 * author         : hagjoon
 * date           : 2023-11-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-24        hagjoon       최초 생성
 */
public class MemberPrincipalDetails implements UserDetails {

    private final Member member;

    public MemberPrincipalDetails(Member member) {
        this.member = member;
    }

    // member 계정의 권한을 담아두기 위해
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(member.getMemberAuth())));
        return authorities;
    }

    //생성자
    public Member getMember(){
        return member;
    }

    //member 계정의 비밀번호를 담아두기 위해
    @Override
    public String getPassword() {
        return member.getMemberPw();
    }

    //member 계정의 아이디를 담아두기 위해
    @Override
    public String getUsername() {
        return member.getMemberId();
    }

    //계정이 만료되지 않았는지를 담아두기 위해 (true : 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않았는지를 담아두기 위해 (true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정의 비밀번호가 만료되지 않았는지를 담아두기 위해 (true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화 되어있는지를 담아두기 위해 (true: 활성화됨)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
