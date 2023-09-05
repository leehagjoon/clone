package com.clonecoding.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : PressReleasBas
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "press_releas_bas")
@AllArgsConstructor
public class PressReleasBas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRRE_SNO")
    private Integer prreSno;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUB_TITLE")
    private String subTitle;

    @Column(name = "CREAT_DT")
    private LocalDateTime creatDt;

    @Column(name = "CREAT_USER")
    private String creatUser;

    @Column(name = "EXPSR_CNT")
    private int expsrCnt;

    @Column(name = "IMG_URL")
    private String imgUrl;
}
