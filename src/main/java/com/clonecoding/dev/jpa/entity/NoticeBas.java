package com.clonecoding.dev.jpa.entity;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notice_bas")
public class NoticeBas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_SNO")
    private Integer noticeSno;

    @Column(name = "MEMBER_SNO")
    private Integer memberSno;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREAT_USER")
    private String creatUser;

    @Column(name = "CREAT_DT")
    private LocalDateTime creatDt;

    @Column(name = "EXPSR_CNT")
    private Integer expsrCnt;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "IMP_YN")
    private String impYn;

    @Builder
    public NoticeBas(Integer noticeSno,
                     Integer memberSno,
                     String title,
                     String content,
                     String creatUser,
                     LocalDateTime creatDt,
                     Integer expsrCnt,
                     String useYn,
                     String impYn){
        this.noticeSno = noticeSno;
        this.memberSno = memberSno;
        this.title = title;
        this.content = content;
        this.creatDt = LocalDateTime.now();
        this.expsrCnt = 0;
        this.creatUser = creatUser;
        this.useYn = "Y";
        this.impYn = "N";
    }
}