package com.webapp.headers.controllers;

import com.webapp.headers.models.Producto;
import com.webapp.headers.services.ProductoService;
import com.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.getProductos();

        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; filename=productos.xls; charset=UTF-8");
        }

        try (PrintWriter out = resp.getWriter()) {

            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Listado de productos Xls</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Listado de productos Xls!</h1>");
                out.println("       <p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a xls</a></p>");
                out.println("       <p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar Json</a></p>");

            }
            out.println("       <table>");
            out.println("           <tr>");
            out.println("               <th>Id</th>");
            out.println("               <th>Nombre</th>");
            out.println("               <th>Tipo</th>");
            out.println("               <th>Precio</th>");
            out.println("           </tr>");
            for (Producto producto : productos) {
                out.println("               <tr>");
                out.println("                   <td>" + producto.getId() + "</td>");
                out.println("                   <td>" + producto.getNombre() + "</td>");
                out.println("                   <td>" + producto.getTipo() + "</td>");
                out.println("                   <td>" + producto.getPrecio() + "</td>");
                out.println("                </tr>");
            }
            out.println("       <table>");
            if (!esXls) {
                out.println("   </body>");
                out.println("</html>");
            }
        }
    }
}
