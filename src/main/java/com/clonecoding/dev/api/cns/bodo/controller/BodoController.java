package com.clonecoding.dev.api.cns.bodo.controller;

import com.clonecoding.dev.api.cns.bodo.service.PrreService;
import com.clonecoding.dev.jpa.entity.PressReleasBas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName    : com.clonecoding.controller
 * fileName       : BodoController
 * author         : hagjoon
 * date           : 2023-10-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-19        hagjoon       최초 생성
 */
@Controller
public class BodoController {

    private final PrreService prreService;

    public BodoController(PrreService prreService) {
        this.prreService = prreService;
    }

    @GetMapping("/bodotext")
    public String getBodoList(Model model){
        model.addAttribute("bodoList",prreService.getBodoList());
        return "/bodotext";
    }

    @GetMapping("/bodotext/bodosearch")
    public String searchBodo(Model model,
                             @RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "searchType",required = false) String searchType){
        List<PressReleasBas> pressReleasBas = null;
        if("all".equals(searchType)){
            pressReleasBas = prreService.getBodoList();
        }else if("title".equals(searchType)){
            pressReleasBas = prreService.searchTitle(keyword);
        }
        model.addAttribute("bodoList", pressReleasBas);
        model.addAttribute("keyword",keyword);
        model.addAttribute("searchType",searchType);
        return "/bodotext";
    }

    @GetMapping("/bododetail/{prreSno}")
    public String getBodoDetail(Model model, @PathVariable Integer prreSno){
        prreService.updateExpsrCnt(prreSno);
        model.addAttribute("bodoDto",prreService.getBodoDetail(prreSno));
        return "/bododetail";
    }
}
