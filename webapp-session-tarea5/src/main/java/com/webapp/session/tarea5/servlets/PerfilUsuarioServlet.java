package com.webapp.session.tarea5.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/perfil-usuario")
public class PerfilUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nombre = (String) session.getAttribute("nombre");

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Perfil usuario</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Perfil usuario "+ nombre +"!</h1>");
            out.println("   <p>Usename: "+ nombre +"</p>");
            out.println("   <p><a href='"+ req.getContextPath() + "/index.jsp'>Volver</a></p>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
