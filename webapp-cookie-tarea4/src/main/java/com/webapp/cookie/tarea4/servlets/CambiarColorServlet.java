package com.webapp.cookie.tarea4.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cambiar-color")
public class CambiarColorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        Cookie c = new Cookie("color", color);
        resp.addCookie(c);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
