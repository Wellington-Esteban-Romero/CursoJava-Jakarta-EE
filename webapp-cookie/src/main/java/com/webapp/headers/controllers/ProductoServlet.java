package com.webapp.headers.controllers;

import com.webapp.headers.models.Producto;
import com.webapp.headers.services.LoginService;
import com.webapp.headers.services.LoginServiceImpl;
import com.webapp.headers.services.ProductoService;
import com.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
        LoginService loginService = new LoginServiceImpl();
        List<Producto> productos = productoService.getProductos();
        Optional<Cookie> cookie = loginService.getCookie(req);

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Listado de productos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Listado de productos!</h1>");
            cookie.ifPresent(value -> out.println("<div style='color: green;'>Hola " + value.getValue() +
                    " Bienvenido! </div>"));
            out.println("       <p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a xls</a></p>");
            out.println("       <p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar Json</a></p>");
            out.println("       <table>");
            out.println("           <tr>");
            out.println("               <th>Id</th>");
            out.println("               <th>Nombre</th>");
            out.println("               <th>Tipo</th>");
            if (cookie.isPresent()) {
                out.println("           <th>Precio</th>");
            }
            out.println("           </tr>");
            for (Producto producto : productos) {
                out.println("               <tr>");
                out.println("                   <td>" + producto.getId() + "</td>");
                out.println("                   <td>" + producto.getNombre() + "</td>");
                out.println("                   <td>" + producto.getTipo() + "</td>");
                if (cookie.isPresent()) {
                    out.println("               <td>" + producto.getPrecio() + "</td>");
                }
                out.println("                </tr>");
            }
            out.println("       <table>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
