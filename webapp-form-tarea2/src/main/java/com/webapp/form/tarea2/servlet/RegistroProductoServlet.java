package com.webapp.form.tarea2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/crear")
public class RegistroProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String nombre = req.getParameter("nombre");
        String fabricante = req.getParameter("fabricante");
        String precio = req.getParameter("precio");
        String categoria = req.getParameter("categoria");

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El campo nombre no puede estar vacío");
        }
        if (precio == null || precio.isBlank()) {
            errores.put("precio", "El campo precio no puede estar vacío");
        } else {
            try {
                Integer.valueOf(precio);
            } catch (NumberFormatException e) {
                errores.put("precio", "El campo precio debe ser un numerico");
            }
        }

        if (fabricante == null || fabricante.isBlank()) {
            errores.put("fabricante", "El campo fabricante no puede estar vacío");
        } else if (!(fabricante.length() >= 4 && fabricante.length() <= 10)) {
            errores.put("fabricante", "El campo fabricante debe contener entre 4 e 10 carácteres");
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
                out.println("       <h1>Resultado Form!</h1>");
                out.println("   <ul>");
                out.println("       <li>Nombre: " + nombre + "</li>");
                out.println("       <li>Precio: " + precio + "</li>");
                out.println("       <li>Fabricante: " + fabricante + "</li>");
                if (!(categoria == null || categoria.isBlank())) {
                    out.println("       <li>Categoria: " + categoria + "</li>");
                }
                out.println("   </ul>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
