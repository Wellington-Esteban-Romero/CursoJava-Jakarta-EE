package com.webapp.listener.tarea7.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index.html")
public class MostrarInfoPersonalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreCompletoRequest = (String) req.getAttribute("nombreCompleto");

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Tarea7 Listener</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Tarea7 Listener!</h1>");
            out.println("   <p>Mi nombre es: " + nombreCompletoRequest + "</p>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
