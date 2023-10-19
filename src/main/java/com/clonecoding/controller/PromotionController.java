package com.clonecoding.controller;

import com.clonecoding.entity.PromotionBas;
import com.clonecoding.service.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
