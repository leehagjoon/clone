package com.clonecoding.dev.jpa.repository;

import com.clonecoding.dev.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
