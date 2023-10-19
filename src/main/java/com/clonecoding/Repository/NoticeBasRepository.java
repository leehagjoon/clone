package com.clonecoding.Repository;

import com.clonecoding.entity.NoticeBas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    // Containing를 통해 Like 검색이 가능하다.
    Page<NoticeBas> findNoticeBasByTitleContaining(String keyword, Pageable pageable);

    @Modifying //Query 어노테이션에서 작성된 조회를 제외한 데이터의 변경이 있는 삽입,수정,삭제 쿼리 사용시 필요한 어노테이션
    @Query("update NoticeBas n set n.expsrCnt = n.expsrCnt + 1 where n.noticeSno = :noticeSno")
    void updateExpsrCnt(Integer noticeSno);

}
