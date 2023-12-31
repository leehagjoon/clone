package com.clonecoding.dev.api.cns.notice.service;

import com.clonecoding.dev.api.acnt.service.MemberPrincipalDetails;
import com.clonecoding.dev.api.cns.notice.model.NoticeModel;
import com.clonecoding.dev.jpa.entity.NoticeBas;
import com.clonecoding.dev.jpa.repository.NoticeBasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
        return noticeBasRepository.findByUseYn("Y",pageable);
    }

    public Page<NoticeBas> searchTitle(String keyword, String useYn,Pageable pageable){
        return noticeBasRepository.findNoticeBasByTitleContainingAndUseYn(keyword, useYn,pageable);
    }


    public NoticeBas getNoticeDetail(Integer noticeSno) {
        Optional<NoticeBas> optional = noticeBasRepository.findById(noticeSno);
        return optional.orElse(null);
    }

    @Transactional
    public void updateExpsrCnt(Integer noticeSno){
        noticeBasRepository.updateExpsrCnt(noticeSno);
    }

    @Transactional
    public void creatWrite(NoticeModel noticeModel, MemberPrincipalDetails principalDetails){
        NoticeBas bas = NoticeBas.builder()
                .noticeSno(noticeModel.getNoticeSno())
                .memberSno(principalDetails.getMemberSno())
                .title(noticeModel.getTitle())
                .content(noticeModel.getContent())
                .creatUser(principalDetails.getMember().getNickName())
                .creatDt(LocalDateTime.now())
                .impYn(noticeModel.getImpYn())
                .useYn(noticeModel.getUseYn())
                .build();
        noticeBasRepository.save(bas);
    }

    @Transactional
    public void updateNotice(NoticeModel model,Integer noticeSno){
        NoticeBas noticeBas = noticeBasRepository.findById(noticeSno)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. " + noticeSno));

        noticeBas.update(model.getTitle(),model.getContent());
    }

    @Transactional
    public void deleteNotice(Integer noticeSno){
        NoticeBas noticeBas = noticeBasRepository.findById(noticeSno)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. noticeSno = " + noticeSno));
        //논리 삭제
        noticeBas.delete();
        noticeBasRepository.save(noticeBas);
    }

}
