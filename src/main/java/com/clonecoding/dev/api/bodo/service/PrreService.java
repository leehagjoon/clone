package com.clonecoding.dev.api.bodo.service;

import com.clonecoding.dev.jpa.entity.PressReleasBas;
import com.clonecoding.dev.jpa.repository.PressReleasBasRepository;
import com.clonecoding.dev.api.bodo.model.PrReModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.clonecoding.service
 * fileName       : PrreService
 * author         : hagjoon
 * date           : 2023-09-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PrreService {


    private final PressReleasBasRepository pressReleasBasRepository;

    public List<PressReleasBas> getBodoList(){
        return pressReleasBasRepository.findAllByOrderByPrreSnoDesc();
    }

    public List<PressReleasBas> searchTitle(String keyword){
        return pressReleasBasRepository.findPressReleasBasByTitleContaining(keyword);
    }

    public PrReModel getBodoDetail(Integer prreSno) {
        Optional<PressReleasBas> optional = pressReleasBasRepository.findById(prreSno);
        PressReleasBas pressReleasBas = optional.get();

        PrReModel prReModel = PrReModel.builder()
                .prreSno(pressReleasBas.getPrreSno())
                .title(pressReleasBas.getTitle())
                .creatDt(pressReleasBas.getCreatDt())
                .creatUser(pressReleasBas.getCreatUser())
                .expsrCnt(pressReleasBas.getExpsrCnt())
                .imgUrl(pressReleasBas.getImgUrl())
                .subTitle(pressReleasBas.getSubTitle())
                .build();
        return prReModel;
    }

    @Transactional
    public void updateExpsrCnt(Integer prreSno){
        pressReleasBasRepository.updateExpsrCnt(prreSno);
    }
}
