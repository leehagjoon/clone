package com.clonecoding.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : NoticeBas
 * author         : hagjoon
 * date           : 2023-09-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        hagjoon       최초 생성
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notice_bas")
@AllArgsConstructor
public class NoticeBas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_SNO")
    private Integer noticeSno;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREAT_USER")
    private String creatUser;

    @Column(name = "CREAT_DT")
    private LocalDateTime creatDt;

    @Column(name = "EXPSR_CNT")
    private int expsrCnt;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "IMP_YN")
    private String impYn;
}