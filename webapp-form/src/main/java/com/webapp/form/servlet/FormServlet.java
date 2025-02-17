package com.webapp.form.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/form")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null && "on".equals(req.getParameter("habilitar"));
        String secret = req.getParameter("secret");

        Map<String,String> errores = new HashMap<>();

        if (username == null || username.isBlank()) {
            errores.put("username", "El usuario no puede estar vacío");
        }
        if (password == null || password.isBlank()) {
            errores.put("password", "El password no puede estar vacío");
        }
        if (email == null || email.isBlank()) {
            errores.put("email", "El email no puede estar vacío");
        } else if (!email.contains("@")) {
            errores.put("email", "El email debe tener un formato válido");
        }
        if (pais == null || pais.isBlank()) {
            errores.put("pais", "El pais no puede estar vacío");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes", "Debe seleccionar un lenguaje");
        }
        if (roles == null || roles.length == 0) {
            errores.put("roles", "Debe seleccionar un role");
        }
        if (idioma == null) {
            errores.put("idioma", "El idioma no puede estar vacío");
        }

        if (errores.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Resultado Form</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Resultado Form</h1>");
                out.println("   <ul>");
                out.println("       <li>Usename: " + username + "</li>");
                out.println("       <li>Password: " + password + "</li>");
                out.println("       <li>Email: " + email + "</li>");
                out.println("       <li>País: " + pais + "</li>");
                out.println("       <li>Lenguajes: <ul>");
                Arrays.stream(lenguajes).forEach(lenguaje -> {
                    out.println("       <li>" + lenguaje + "</li>");
                });
                out.println("       </ul></li>");
                out.println("       <li>Roles: <ul>");
                Arrays.stream(roles).forEach(rol -> {
                    out.println("       <li>" + rol + "</li>");
                });
                out.println("       </ul></li>");
                out.println("       <li>Idioma: " + idioma + "</li>");
                out.println("       <li>Habilitar: " + habilitar + "</li>");
                out.println("       <li>Secreto: " + secret + "</li>");
                out.println("   </ul>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
//                errores.forEach(error -> {
//                    out.println("       <li>" + error + "</li>");
//                });
//                out.println("<p><a href=\"/webapp-form/index.jsp\">Volver</a></
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
