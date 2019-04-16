package com.cafe24.springmvcstudy.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/regist/step3")
@Slf4j
public class ApiTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("filter => API Token Filter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        log.info("req header => {}", request.getHeader("x-auth-token"));
        if (request.getHeader("x-auth-token") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "auth error");
        } else {
            chain.doFilter(req, res);
        }
    }
}