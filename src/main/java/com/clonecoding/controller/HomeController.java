package com.clonecoding.controller;

import com.clonecoding.entity.NoticeBas;
import com.clonecoding.model.NoticeDto;
import com.clonecoding.model.PrReDto;
import com.clonecoding.model.PromotionDto;
import com.clonecoding.service.NoticeService;
import com.clonecoding.service.PromtionService;
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
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
