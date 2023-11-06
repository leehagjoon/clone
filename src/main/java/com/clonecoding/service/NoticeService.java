package com.clonecoding.service;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.Repository.NoticeBasRepository;
import com.clonecoding.model.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Page<NoticeBas> getNoticeList(Pageable pageable) {
    return noticeBasRepository.findAll(pageable);
    }

    public Page<NoticeBas> searchTitle(String keyword, Pageable pageable){
        return noticeBasRepository.findNoticeBasByTitleContaining(keyword, pageable);
    }


    public NoticeDto getNoticeDetail(Integer noticeSno) {
        Optional<NoticeBas> optional = noticeBasRepository.findById(noticeSno);
        NoticeBas noticeBas = optional.get();

        NoticeDto noticeDto = NoticeDto.builder()
                .noticeSno(noticeBas.getNoticeSno())
                .title(noticeBas.getTitle())
                .creatDt(noticeBas.getCreatDt())
                .creatUser(noticeBas.getCreatUser())
                .expsrCnt(noticeBas.getExpsrCnt())
                .content(noticeBas.getContent())
                .build();
        return noticeDto;
    }

    @Transactional
    public void updateExpsrCnt(Integer noticeSno){
        noticeBasRepository.updateExpsrCnt(noticeSno);
    }

    @Transactional
    public void creatWrite(NoticeDto noticeDto){
        NoticeBas noticeBas = noticeDto.toEntity();
        noticeBas.setCreatDt(LocalDateTime.now());
        noticeBas.setImpYn("N");
        noticeBas.setUseYn("Y");
        noticeBasRepository.save(noticeBas);
    }

}
