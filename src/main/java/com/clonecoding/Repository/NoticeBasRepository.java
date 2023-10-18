package com.clonecoding.Repository;

import com.clonecoding.entity.NoticeBas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Modifying //Query 어노테이션에서 작성된 조회를 제외한 데이터의 변경이 있는 삽입,수정,삭제 쿼리 사용시 필요한 어노테이션
    @Query("update NoticeBas n set n.expsrCnt = n.expsrCnt + 1 where n.noticeSno = :noticeSno")
    void updateExpsrCnt(Integer noticeSno);

}
