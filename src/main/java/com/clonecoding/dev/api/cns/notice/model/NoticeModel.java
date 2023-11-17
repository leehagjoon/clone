package com.clonecoding.dev.api.cns.notice.model;

import com.clonecoding.dev.jpa.entity.NoticeBas;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.model
 * fileName       : NoticeDto
 * author         : hagjoon
 * date           : 2023-09-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        hagjoon       최초 생성
 */
@Data
public class NoticeModel {

    private Integer noticeSno;

    private String title;

    private String content;

    private String creatUser;

    private String useYn;

    private String impYn;

    private LocalDateTime creatDt;

    private int expsrCnt;
    @Builder
    public NoticeBas toEntity(){
        return NoticeBas.builder()
                .noticeSno(noticeSno)
                .title(title)
                .content(content)
                .creatUser(creatUser)
                .useYn(useYn)
                .impYn(impYn)
                .creatDt(creatDt)
                .expsrCnt(expsrCnt)
                .build();
    }
}