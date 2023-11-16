package com.clonecoding.dev.jpa.repository;

import com.clonecoding.dev.jpa.entity.PromotionBas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : PromotionBasRepository
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Repository
public interface PromotionBasRepository extends JpaRepository<PromotionBas,Integer> {
    List<PromotionBas> findAllByOrderByProSnoDesc();

    List<PromotionBas> findPromotionBasByTitleContaining(String keyword);
}
