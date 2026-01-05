package com.example.study.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // JSON 요청 본문 읽기
            Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), new com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {});

            String username = credentials.get("userName");
            String password = credentials.get("password");

            // null 체크
            if (username == null || password == null) {
                throw new IllegalArgumentException("userName과 password는 필수입니다.");
            }

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, null));
        } catch (IOException e) {
            throw new RuntimeException("요청 본문을 파싱할 수 없습니다.", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // JWT 토큰 발급 등의 로직을 여기에 작성할 수 있습니다
        System.out.println(" success    " );
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 로그인 실패 처리 로직
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\": \"인증에 실패했습니다.\"}");
    }
}
