package com.webapp.servlet.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Parámetros Get de la url</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Parámetros Get de la url</h1>");
        if (saludo != null & nombre != null & apellido != null) {
            out.println("   <h2>Saludo: " + saludo + " " + nombre + " " + apellido+ "</h2>");
        } else if (saludo != null) {
            out.println("   <h2>Saludo:" + saludo + "</h2>");
        } else if (nombre != null) {
            out.println("   <h2>Mi nombre es: " + nombre + "</h2>");
        } else {
            out.println("   <h2>No se ha pasado ningun parámetro</h2>");
        }
        try {
            Integer edad = Integer.valueOf(req.getParameter("edad"));
            out.println("<h3>La edad es: " + edad + "</h3>");
        } catch (NumberFormatException e) {
            out.println("<h3> La edad no ha sido enviada </h3>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
