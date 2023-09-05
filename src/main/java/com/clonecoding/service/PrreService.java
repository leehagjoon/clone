package com.clonecoding.service;

import com.clonecoding.entity.PressReleasBas;
import com.clonecoding.entity.PressReleasBasRepository;
import com.clonecoding.model.PrReDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<PrReDto> getBodoList(){
        List<PressReleasBas> pressReleasBas = pressReleasBasRepository.findAllByOrderByPrreSnoDesc();
        List<PrReDto> prReDtoList = new ArrayList<>();
        for(PressReleasBas bas: pressReleasBas){
            prReDtoList.add(this.convertEntityToDto(bas));
        }
        return prReDtoList;
    }

    private PrReDto convertEntityToDto(PressReleasBas bas){
        return PrReDto.builder()
                .prreSno(bas.getPrreSno())
                .title(bas.getTitle())
                .subTitle(bas.getSubTitle())
                .creatDt(bas.getCreatDt())
                .creatUser(bas.getCreatUser())
                .expsrCnt(bas.getExpsrCnt())
                .imgUrl(bas.getImgUrl())
                .build();
    }

    public PrReDto getBodoDetail(Integer prreSno) {
        Optional<PressReleasBas> optional = pressReleasBasRepository.findById(prreSno);
        PressReleasBas pressReleasBas = optional.get();

        PrReDto prReDto = PrReDto.builder()
                .prreSno(pressReleasBas.getPrreSno())
                .title(pressReleasBas.getTitle())
                .creatDt(pressReleasBas.getCreatDt())
                .creatUser(pressReleasBas.getCreatUser())
                .expsrCnt(pressReleasBas.getExpsrCnt())
                .imgUrl(pressReleasBas.getImgUrl())
                .subTitle(pressReleasBas.getSubTitle())
                .build();
        return prReDto;
    }
}
