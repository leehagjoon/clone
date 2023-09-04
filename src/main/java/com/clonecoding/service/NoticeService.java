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
     List<NoticeBas> noticeBas = noticeBasRepository.findAll();
     List<NoticeDto> noticeDtoList = new ArrayList<>();

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
