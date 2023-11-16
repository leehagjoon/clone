package com.clonecoding.dev.api.cns.promotion.controller;

import com.clonecoding.dev.jpa.entity.PromotionBas;
import com.clonecoding.dev.api.cns.promotion.service.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName    : com.clonecoding.controller
 * fileName       : PromtionController
 * author         : hagjoon
 * date           : 2023-10-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-19        hagjoon       최초 생성
 */
@Controller
public class PromotionController {

    private final PromotionService promtionService;

    public PromotionController(PromotionService promtionService) {
        this.promtionService = promtionService;
    }

    @GetMapping("/promotion")
    public String getPromotion(Model model){
        model.addAttribute("promList", promtionService.getPromList());
        return "/promotion";
    }

    @GetMapping("/promotion/promsearch")
    public String searchPromo(Model model,
                              @RequestParam(value = "keyword", required = false)String keyword,
                              @RequestParam(value = "searchType", required = false)String searchType){
        List<PromotionBas> promotionBas = null;
        if("all".equals(searchType)){
            promotionBas = promtionService.getPromList();
        }else if("title".equals(searchType)){
            promotionBas = promtionService.searchProm(keyword);
        }
        model.addAttribute("promList",promotionBas);
        model.addAttribute("keyword",keyword);
        model.addAttribute("searchType",searchType);
        return "/promotion";
    }
}
