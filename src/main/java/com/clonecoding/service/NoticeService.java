package com.clonecoding.service;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.entity.NoticeBasRepository;
import com.clonecoding.model.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.clonecoding.service
 * fileName       : NoticeService
 * author         : hagjoon
 * date           : 2023-09-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeBasRepository noticeBasRepository;

    public List<NoticeDto> getNoticeList() {
        // repository에서 jpa 기능인 findAll() 로 전체 리스트를 불러온다.
     List<NoticeBas> noticeBas = noticeBasRepository.findAllByOrderByNoticeSnoDesc();
        // NoticeDto를 entity화 해서 초기화 시켜준다.
     List<NoticeDto> noticeDtoList = new ArrayList<>();

     // for문을 돌려서 entity에 있는 목록과 dto에 있는 모델을 맞춰주고 list에 저장시켜준다.
     for(NoticeBas bas : noticeBas){
         NoticeDto noticeDto = NoticeDto.builder()
                 .noticeSno(bas.getNoticeSno())
                 .title(bas.getTitle())
                 .creatUser(bas.getCreatUser())
                 .creatDt(bas.getCreatDt())
                 .expsrCnt(bas.getExpsrCnt())
                 .build();
         noticeDtoList.add(noticeDto);
     }
    return noticeDtoList;
    }
}
