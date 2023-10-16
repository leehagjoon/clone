package com.clonecoding.service;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.Repository.NoticeBasRepository;
import com.clonecoding.model.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
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
    private static final int BLOCK_PAGE_NUM_COUNT = 10; //블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 5; // 한 페이지에 존재하는 게시글 수

    public List<NoticeDto> getNoticeList(Integer pageNum) {
        Page<NoticeBas> pageing = noticeBasRepository
                .findAll(PageRequest
                        .of(pageNum -1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "noticeSno")));

        // repository에서 jpa 기능인 findAll() 로 전체 리스트를 불러온다.
//     List<NoticeBas> noticeBas = noticeBasRepository.findAllByOrderByNoticeSnoDesc();
     List<NoticeBas> noticeBas = pageing.getContent();
        // NoticeDto를 entity화 해서 초기화 시켜준다.
     List<NoticeDto> noticeDtoList = new ArrayList<>();

     // for문을 돌려서 entity에 있는 목록과 dto에 있는 모델을 맞춰주고 list에 저장시켜준다.
     for(NoticeBas bas : noticeBas){
         noticeDtoList.add(this.convertEntityToDto(bas));
     }
    return noticeDtoList;
    }

    public Integer[] getPageList(Integer curPageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 수
        Double postTotalCount = Double.valueOf(this.getNoticeCount());

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil(postTotalCount/PAGE_POST_COUNT));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        //페이지 번호 할당
        for(int val = curPageNum, i=0; val<=blockLastPageNum;val++,i++){
            pageList[i] = val;
        }
        return pageList;
    }

    private long getNoticeCount() {
        return noticeBasRepository.count();
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

    public List<NoticeDto> searchList(String keyword, Integer pageNum){
        Page<NoticeBas> pageing = noticeBasRepository
                .findAll(PageRequest
                        .of(pageNum -1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "noticeSno")));
//        List<NoticeBas> noticeBas = noticeBasRepository.findByTitleContainingOrderByNoticeSnoDesc(keyword);
        List<NoticeBas> noticeBas;

        if (StringUtils.hasLength(keyword)) {
            noticeBas = pageing.getContent();
        } else {
            noticeBas = noticeBasRepository.findByTitleContainingOrderByNoticeSnoDesc(keyword);
        }

        List<NoticeDto> noticeDtoList = new ArrayList<>();

        for (NoticeBas bas : noticeBas) {
            noticeDtoList.add(this.convertEntityToDto(bas));
        }
        return noticeDtoList;
    }


    private NoticeDto convertEntityToDto(NoticeBas bas) {
        return NoticeDto.builder()
                .noticeSno(bas.getNoticeSno())
                .title(bas.getTitle())
                .creatUser(bas.getCreatUser())
                .creatDt(bas.getCreatDt())
                .expsrCnt(bas.getExpsrCnt())
                .build();
    }

    @Transactional
    public void updateExpsrCnt(Integer noticeSno){
        noticeBasRepository.updateExpsrCnt(noticeSno);
    }
}
