package com.clonecoding.dev.api.cns.promotion.model;

import com.clonecoding.dev.jpa.entity.PromotionBas;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.model
 * fileName       : PromotionDto
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Data
@Builder
public class PromotionModel {

    private Integer proSno;

    private String title;

    private String subTitle;

    private LocalDateTime creatDt;

    private String linkUrl;

    private String imgUrl;

    public PromotionBas toentity(){
        PromotionBas promotionBas = PromotionBas.builder()
                .proSno(proSno)
                .title(title)
                .subTitle(subTitle)
                .creatDt(creatDt)
                .linkUrl(linkUrl)
                .imgUrl(imgUrl)
                .build();
        return promotionBas;
    }
}
