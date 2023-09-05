package com.clonecoding;

import com.clonecoding.entity.PressReleasBas;
import com.clonecoding.entity.PressReleasBasRepository;
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
public class PrreTest {

    @Autowired
    PressReleasBasRepository pressReleasBasRepository;

    @Test
    void save() {
        PressReleasBas bas = PressReleasBas.builder()
                .title("9번 보도자료 입니다.")
                .subTitle("9번 보도자료의 부 제목 입니다.")
                .imgUrl("이미지2")
                .creatDt(LocalDateTime.now())
                .creatUser("관리자")
                .expsrCnt(0)
                .build();
        pressReleasBasRepository.save(bas);
    }
}
