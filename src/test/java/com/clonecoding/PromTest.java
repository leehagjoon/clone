package com.clonecoding;

import com.clonecoding.entity.PromotionBas;
import com.clonecoding.Repository.PromotionBasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding
 * fileName       : PrreTest
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@SpringBootTest
public class PromTest {

    @Autowired
    PromotionBasRepository promotionBasRepository;

    @Test
    void save() {
        PromotionBas bas = PromotionBas.builder()
                .title("1번 보도자료 입니다.")
                .subTitle("1번 보도자료의 부 제목 입니다.")
                .imgUrl("이미지1")
                .creatDt(LocalDateTime.now())
                .linkUrl("링크1")
                .build();
        promotionBasRepository.save(bas);
    }
}
