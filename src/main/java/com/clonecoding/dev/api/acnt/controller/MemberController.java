package com.clonecoding.dev.api.acnt.controller;

import com.clonecoding.dev.api.acnt.model.MemberModel;
import com.clonecoding.dev.api.acnt.service.MemberPrincipalDetails;
import com.clonecoding.dev.api.acnt.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.clonecoding.dev.api.acnt.controller
 * fileName       : MemberController
 * author         : hagjoon
 * date           : 2023-11-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-20        hagjoon       최초 생성
 */
@Controller
@Slf4j
@RequestMapping("/api/acnt")
public class MemberController {


    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    private boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return false;
        }
        return authentication.isAuthenticated();
    }


    @GetMapping("/signup")
    public String join(){
        return "signup";
    }

    @PostMapping(value = "/join" , produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, String>> joinMember(@RequestBody MemberModel memberModel){
        Map<String, String> res = new HashMap<>();
        try{
            memberService.join(memberModel);
            log.info("???? : {}", memberModel );
            res.put("message","회원가입 성공");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            log.info("회원 등록 중 오류 발생 : {} ", e);
            res.put("message", "회원가입 실패");
            return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request,
                        @AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails){
        if(isAuthenticated()){
            if(memberPrincipalDetails == null){
                return "/logout";
            }
            return "/home";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "home";
    }
}
