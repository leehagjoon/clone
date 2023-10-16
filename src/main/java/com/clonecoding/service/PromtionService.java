package com.clonecoding.service;

import com.clonecoding.entity.PromotionBas;
import com.clonecoding.Repository.PromotionBasRepository;
import com.clonecoding.model.PromotionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PromtionService {

    private final PromotionBasRepository promotionBasRepository;


    public List<PromotionDto> getPromList() {
        List<PromotionBas> promotionBas = promotionBasRepository.findAllByOrderByProSnoDesc();
        List<PromotionDto> promotionDtoList = new ArrayList<>();
        for(PromotionBas bas : promotionBas){
            promotionDtoList.add(this.convertEntityToDto(bas));
        }
        return promotionDtoList;
    }

    private PromotionDto convertEntityToDto(PromotionBas bas) {
        return PromotionDto.builder()
                .proSno(bas.getProSno())
                .title(bas.getTitle())
                .subTitle(bas.getSubTitle())
                .creatDt(bas.getCreatDt())
                .linkUrl(bas.getLinkUrl())
                .imgUrl(bas.getImgUrl())
                .build();
    }
}
