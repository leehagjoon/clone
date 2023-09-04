package com.clonecoding;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.entity.NoticeBasRepository;
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
        NoticeBas noticeBas = NoticeBas.builder()
                .title("2번 게시글 제목")
                .content("2번 게시글 본문")
                .creatUser("관리자")
                .creatDt(LocalDateTime.now())
                .expsrCnt(0)
                .useYn("Y")
                .impYn("N")
                .build();

        noticeBasRepository.save(noticeBas);
    }
}
