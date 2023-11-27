package com.clonecoding.dev.api.cns.notice.model;

import com.clonecoding.dev.jpa.entity.NoticeBas;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.dev.api.cns.notice.model
 * fileName       : NoticeDetailRS
 * author         : hagjoon
 * date           : 2023-11-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-27        hagjoon       최초 생성
 */
@Data
@Builder
public class NoticeDetailRS {

    private String title;
    private LocalDateTime creatDt;
    private String creatUser;
    private int expsrCnt;
    private String content;

}
