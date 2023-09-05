package com.clonecoding.model;

import com.clonecoding.entity.PressReleasBas;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.clonecoding.model
 * fileName       : PrReDto
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Data
public class PrReDto {

    private Integer prreSno;

    private String title;

    private String subTitle;

    private LocalDateTime creatDt;

    private String creatUser;

    private int expsrCnt;

    private String imgUrl;

    public PressReleasBas toentity(){
        PressReleasBas pressReleasBas = PressReleasBas.builder()
                .prreSno(prreSno)
                .title(title)
                .subTitle(subTitle)
                .creatDt(creatDt)
                .creatUser(creatUser)
                .expsrCnt(expsrCnt)
                .imgUrl(imgUrl)
                .build();
        return pressReleasBas;
    }
}
