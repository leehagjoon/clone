package com.clonecoding.controller;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.model.NoticeDto;
import com.clonecoding.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String getNoticeList(Model model, @PageableDefault(size = 10, sort = "noticeSno",direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("noticeList",noticeService.getNoticeList(pageable));
        return "/notice";
    }

    @GetMapping("/notice/search")
    public String searchNotices(Model model,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "searchType", required = false) String searchType,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @PageableDefault(size = 10, sort = "noticeSno", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NoticeBas> searchList = null;

        if ("all".equals(searchType)) {
            searchList = noticeService.getNoticeList(pageable);
        } else if ("title".equals(searchType)) {
            searchList = noticeService.searchTitle(keyword, pageable);
        }

        model.addAttribute("noticeList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);

        return "/notice";
    }

    @GetMapping("/detail/{noticeSno}")
    public String getNoticeDetail(Model model, @PathVariable Integer noticeSno) {
        noticeService.updateExpsrCnt(noticeSno);
        model.addAttribute("noticeDto", noticeService.getNoticeDetail(noticeSno));
        return "/detail";
    }

    @GetMapping("/noticewrite")
    public String getNoticeWrite(){
        return "/noticewrite";
    }

    @PostMapping(value = "/noticewrite/write",produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String,String>> creatWrite(@RequestBody NoticeDto dto){
        Map<String, String> res = new HashMap<>();
        try{
            noticeService.creatWrite(dto);
            log.info("정보가 잘 저장이 될까나?? : {} ",dto);
            res.put("message","글 등록에 성공");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            res.put("message","글 작성에 실패");
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
