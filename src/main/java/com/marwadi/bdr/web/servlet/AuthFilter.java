package com.marwadi.bdr.web.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // The page path without the context path, e.g. "/donors"
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Pages anyone can open without logging in
        boolean publicPage = path.equals("/")
                || path.equals("/login")
                || path.equals("/logout")
                || path.startsWith("/css/")
                || path.startsWith("/js/");

        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("loggedUser") != null;

        if (publicPage || loggedIn) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}