package com.clonecoding.dev.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * packageName    : com.clonecoding.dev.config.security
 * fileName       : MemberAuthSuccessHandler
 * author         : hagjoon
 * date           : 2023-11-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-25        hagjoon       최초 생성
 */
public class MemberAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 로그인 성공시 이동할 페이지
        setDefaultTargetUrl("/");
        // 로그인 성공시 이동할 페이지로 이동
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
