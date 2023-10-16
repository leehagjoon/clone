package com.clonecoding.Repository;

import com.clonecoding.entity.PressReleasBas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : PressReleasBasRepository
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Repository
public interface PressReleasBasRepository extends JpaRepository<PressReleasBas, Integer> {
    List<PressReleasBas> findAllByOrderByPrreSnoDesc();

    @Modifying
    @Query("update PressReleasBas p set p.expsrCnt = p.expsrCnt + 1 where  p.prreSno = :prreSno")
    void updateExpsrCnt(Integer prreSno);
}
