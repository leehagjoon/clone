package com.clonecoding.controller;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.model.NoticeDto;
import com.clonecoding.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName    : com.clonecoding.controller
 * fileName       : NoticeController
 * author         : hagjoon
 * date           : 2023-10-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-16        hagjoon       최초 생성
 */
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String getNoticeList(Model model){
        List<NoticeBas> noticeDtoList = noticeService.getNoticeList();
        model.addAttribute("noticeList",noticeDtoList);
        return "/notice";
    }

    @GetMapping("/notice/search")
    public String searchNotices(Model model , @RequestParam(value = "keyword",required = false) String keyword, @RequestParam(value = "searchType",required = false) String searchType){
        List<NoticeBas> searchList = null;
        if("all".equals(searchType)){
            searchList = noticeService.getNoticeList();
        }else if("title".equals(searchType)){
            searchList = noticeService.searchTitle(keyword);
        }
        model.addAttribute("noticeList", searchList);

        return "/notice";
    }


    @GetMapping("/detail/{noticeSno}")
    public String getNoticeDetail(Model model, @PathVariable Integer noticeSno){
        noticeService.updateExpsrCnt(noticeSno);
        model.addAttribute("noticeDto",noticeService.getNoticeDetail(noticeSno));
        return "/detail";
    }
}
