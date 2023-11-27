package com.clonecoding.dev.api.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        log.warn("dd");

        return "home";
    }

}
