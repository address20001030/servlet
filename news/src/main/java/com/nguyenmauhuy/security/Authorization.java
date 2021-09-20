package com.nguyenmauhuy.security;

import com.nguyenmauhuy.entity.User;
import com.nguyenmauhuy.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/form/*"})
public class Authorization implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        var url = req.getRequestURI();

        if (url.startsWith("/login") || url.equals("/") || url.startsWith("/logout") || url.startsWith("/template") || url.startsWith("/api")) {
            chain.doFilter(req, res);
            return;
        }
        var user = (User) SessionUtil.get("USER", req);
        if (user == null) {
            var requestDispatcher = req.getRequestDispatcher("/views/Login.jsp");
            requestDispatcher.forward(req, res);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
