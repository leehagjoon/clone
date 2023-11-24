package com.clonecoding.dev.jpa.repository;

import com.clonecoding.dev.api.acnt.model.MemberModel;
import com.clonecoding.dev.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName    : com.clonecoding.dev.jpa.repository
 * fileName       : MemberRepository
 * author         : hagjoon
 * date           : 2023-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-17        hagjoon       최초 생성
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    //중복된 회원이 있는지 검사하기 위해서 아이디로 회원을 검사할 수 있도록 쿼리 메서드 작성
    Member findByMemberId(String username);
}
