package com.clonecoding.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    // noticeSno 필드를 기준으로 내림차순으로 정렬된 리스트를 가져옵니다.
    List<NoticeBas> findAllByOrderByNoticeSnoDesc();

    List<NoticeBas> findByTitleContainingOrderByNoticeSnoDesc(String keyword);
}
