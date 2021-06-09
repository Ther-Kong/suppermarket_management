package com.example.supermarket_management.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import com.example.supermarket_management.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        JwtUtil.verify(token);
        return true;
    }
}
