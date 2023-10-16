package com.clonecoding.controller;

import com.clonecoding.model.NoticeDto;
import com.clonecoding.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        List<NoticeDto> noticeDtoList = noticeService.getNoticeList();
        model.addAttribute("noticeList",noticeDtoList);
        return "/notice";
    }
}
