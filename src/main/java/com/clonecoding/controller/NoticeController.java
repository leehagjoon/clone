package com.clonecoding.controller;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.model.NoticeDto;
import com.clonecoding.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public ResponseEntity<Page<NoticeBas>> getNoticeList(@PageableDefault(size = 10, sort = "noticeSno",direction = Sort.Direction.DESC)Pageable pageable){
        Page<NoticeBas> noticeBas = noticeService.getNoticeList(pageable);
        return ResponseEntity.ok(noticeBas);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NoticeBas>> searchNotices(
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

        return ResponseEntity.ok(searchList);
    }

    @GetMapping("/{noticeSno}")
    public ResponseEntity<NoticeDto> getNoticeDetail(@PathVariable Integer noticeSno) {
        noticeService.updateExpsrCnt(noticeSno);
        NoticeDto notice = noticeService.getNoticeDetail(noticeSno);
        return ResponseEntity.ok(notice);
    }
}
