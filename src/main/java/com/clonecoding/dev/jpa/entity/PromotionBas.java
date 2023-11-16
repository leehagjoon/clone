package com.clonecoding.dev.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.entity
 * fileName       : PromotionBas
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
@Table(name = "promotion_bas")
@AllArgsConstructor
public class PromotionBas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_SNO")
    private Integer proSno;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUB_TITLE")
    private String subTitle;

    @Column(name = "CREAT_DT")
    private LocalDateTime creatDt;

    @Column(name = "LINK_URL")
    private String linkUrl;

    @Column(name = "IMG_URL")
    private String imgUrl;
}
