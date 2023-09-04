package com.clonecoding.model;

import com.clonecoding.entity.NoticeBas;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
public class NoticeDto {

    private Integer noticeSno;

    private String title;

    private String content;

    private String creatUser;

    private String useYn;

    private String impYn;

    private LocalDateTime creatDt;

    private int expsrCnt;

    public NoticeBas toEntity(){
        NoticeBas noticeBas = NoticeBas.builder()
                .noticeSno(noticeSno)
                .title(title)
                .content(content)
                .creatUser(creatUser)
                .useYn(useYn)
                .impYn(impYn)
                .creatDt(creatDt)
                .expsrCnt(expsrCnt)
                .build();
        return noticeBas;
    }
}
