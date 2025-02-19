package com.webapp.headers.controllers;

import com.webapp.headers.services.LoginService;
import com.webapp.headers.services.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService loginService = new LoginServiceImpl();
        Optional<Cookie> cookie = loginService.getCookie(req);
        if (cookie.isPresent()) {
            Cookie newCookie = new Cookie("username","");
            newCookie.setMaxAge(0);
            resp.addCookie(newCookie);
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
