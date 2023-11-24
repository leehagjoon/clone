package com.clonecoding.dev.config.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * packageName    : com.clonecoding.dev.config.security.config
 * fileName       : MemberAuthFailureHandler
 * author         : hagjoon
 * date           : 2023-11-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-24        hagjoon       최초 생성
 */
public class MemberAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException{
        // 실패 메시지
        HttpSession session = request.getSession();
        //세션에 실패 메세지
        session.setAttribute("loginErrorMessage",exception.getMessage());
        //로그인 실패시 이동할 페이지
        setDefaultFailureUrl("/api/acnt/login");
        super.onAuthenticationFailure(request,response,exception);
    }
}
