package com.webapp.session.controllers;

import com.webapp.session.models.Producto;
import com.webapp.session.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        ProductoService productoService = new ProductoServiceImpl();
        LoginService loginService = new LoginServiceSessionImpl();
        List<Producto> productos = productoService.getProductos();
        Optional<String> username = loginService.getUsername(req);
        String mensajeRequest = req.getParameter("mensaje");
        String mensajeGlobal = getServletContext().getAttribute("mensaje").toString();

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Listado de productos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Listado de productos!</h1>");
            username.ifPresent(value -> out.println("<div style='color: green;'>Hola " + value +
                    " Bienvenido! </div>"));
            out.println("       <table>");
            out.println("           <tr>");
            out.println("               <th>Id</th>");
            out.println("               <th>Nombre</th>");
            out.println("               <th>Tipo</th>");
            if (username.isPresent()) {
                out.println("           <th>Precio</th>");
            }
            out.println("           </tr>");
            for (Producto producto : productos) {
                out.println("               <tr>");
                out.println("                   <td>" + producto.getId() + "</td>");
                out.println("                   <td>" + producto.getNombre() + "</td>");
                out.println("                   <td>" + producto.getTipo() + "</td>");
                if (username.isPresent()) {
                    out.println("               <td>" + producto.getPrecio() + "</td>");
                    out.println("               <td><a href='" + req.getContextPath() + "/carro/agregar?id=" +
                            producto.getId() + "'>Agregar al carro</a>" + "</td>");
                }
                out.println("                </tr>");
            }
            out.println("       <table>");
            out.println("           <p>" + mensajeGlobal +"</p>");
            out.println("           <p>" + mensajeRequest + "</p>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
