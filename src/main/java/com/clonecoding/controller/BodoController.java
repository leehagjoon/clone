package com.clonecoding.controller;

import com.clonecoding.service.PrreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/bododetail/{prreSno}")
    public String getBodoDetail(Model model, @PathVariable Integer prreSno){
        prreService.updateExpsrCnt(prreSno);
        model.addAttribute("bodoDto",prreService.getBodoDetail(prreSno));
        return "/bododetail";
    }
}
