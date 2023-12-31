package com.clonecoding.dev.api.cns.bodo.model;

import com.clonecoding.dev.jpa.entity.PressReleasBas;
import lombok.Builder;
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
@Builder
public class PrReModel {

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
