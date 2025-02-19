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
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "Wellington";
    private static final String PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService loginService = new LoginServiceImpl();
        Optional<Cookie> cookie = loginService.getCookie(req);

        if (cookie.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Hola " + cookie.get().getValue() +"</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Hola " + cookie.get().getValue() + " ya has iniciado sesión</h1>");
                out.println("   <p><a href='"+ req.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("   <p><a href='"+ req.getContextPath() + "/logout'>Cerrar sesión</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie cookie = new Cookie("username", username);
            resp.addCookie(cookie);

            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Login correcto</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Login correcto!</h1>");
                out.println("   <h3>" + username + " Has iniciado sesión correctamente!</h3>");
                out.println("   <p><a href='"+ req.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
        }
    }
}
