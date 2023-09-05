package com.clonecoding.controller;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.model.NoticeDto;
import com.clonecoding.model.PrReDto;
import com.clonecoding.service.NoticeService;
import com.clonecoding.service.PrreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.clonecoding.controller
 * fileName       : HomeController
 * author         : hagjoon
 * date           : 2023-09-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        hagjoon       최초 생성
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NoticeService noticeService;

    private final PrreService prreService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/notice")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum){
        List<NoticeDto> noticeDtoList = noticeService.getNoticeList(pageNum);
        Integer[] pageList = noticeService.getPageList(pageNum);

        model.addAttribute("noticeList",noticeDtoList);
        model.addAttribute("pageList",pageList);

        return "/notice";
    }

    @GetMapping("/detail/{noticeSno}")
    public String detail(@PathVariable("noticeSno") Integer noticeSno, Model model){
        NoticeDto dto = noticeService.getNoticeDetail(noticeSno);
        noticeService.updateExpsrCnt(noticeSno);
        model.addAttribute("noticeDto", dto);

        return "/detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<NoticeDto> noticeDtoList = noticeService.searchList(keyword);
        model.addAttribute("noticeList",noticeDtoList);

        return "/notice";
    }

    @GetMapping("/bodotext")
    public String bodoList(Model model){
        List<PrReDto> prReDtoList = prreService.getBodoList();
        model.addAttribute("bodoList",prReDtoList);
        return "/bodotext";
    }

    @GetMapping("/bododetail/{prreSno}")
    public String bodoDetail(@PathVariable("prreSno") Integer prreSno, Model model){
        PrReDto dto = prreService.getBodoDetail(prreSno);
        model.addAttribute("bodoDto",dto);

        return "/bododetail";
    }
}
