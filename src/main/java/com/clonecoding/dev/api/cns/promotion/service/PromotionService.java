package com.clonecoding.dev.api.cns.promotion.service;

import com.clonecoding.dev.jpa.entity.PromotionBas;
import com.clonecoding.dev.jpa.repository.PromotionBasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.clonecoding.service
 * fileName       : PromtionService
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionBasRepository promotionBasRepository;


    public List<PromotionBas> getPromList() {
        return promotionBasRepository.findAllByOrderByProSnoDesc();
    }

    public List<PromotionBas> searchProm(String keyword){
        return promotionBasRepository.findPromotionBasByTitleContaining(keyword);
    }
}
