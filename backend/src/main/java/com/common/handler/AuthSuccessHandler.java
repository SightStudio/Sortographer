package com.common.handler;

import com.app.dto.ResponseDTO;
import com.common.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ResponseDTO repl = new ResponseDTO("로그인에 성공하였습니다.", HttpStatus.OK,true);

        // email jwt로 리턴
        repl.addData("jwt", jwtTokenProvider.createToken(userDetails.getUsername()));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(repl));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
