package com.clonecoding.controller;

import com.clonecoding.model.NoticeDto;
import com.clonecoding.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/notice")
    public String list(Model model){
        List<NoticeDto> noticeDtoList = noticeService.getNoticeList();
        model.addAttribute("noticeList",noticeDtoList);

        return "/notice";
    }

    @GetMapping("/detail/{noticeSno}")
    public String detail(@PathVariable("noticeSno") Integer noticeSno, Model model){
        NoticeDto dto = noticeService.getNoticeDetail(noticeSno);

        model.addAttribute("noticeDto", dto);

        return "/detail";
    }
}
