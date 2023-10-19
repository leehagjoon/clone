package com.clonecoding;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.Repository.NoticeBasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding
 * fileName       : NoticeTests
 * author         : hagjoon
 * date           : 2023-09-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        hagjoon       최초 생성
 */
@SpringBootTest
public class NoticeTests {

    @Autowired
    NoticeBasRepository noticeBasRepository;

    @Test
    void save() {
        for(int i = 51; i<=99; i++) {
            NoticeBas noticeBas = NoticeBas.builder()
                    .title(i + "번 게시글 제목")
                    .content(i + "번 게시글 본문")
                    .creatUser("관리자")
                    .creatDt(LocalDateTime.now())
                    .expsrCnt(0)
                    .useYn("Y")
                    .impYn("N")
                    .build();

            noticeBasRepository.save(noticeBas);
        }
    }
}
