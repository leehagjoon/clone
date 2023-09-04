package com.clonecoding.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : NoticeBasRepository
 * author         : hagjoon
 * date           : 2023-09-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        hagjoon       최초 생성
 */
@Repository
public interface NoticeBasRepository extends JpaRepository<NoticeBas, Integer> {
}
