package com.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.headers.models.Producto;
import com.webapp.headers.services.ProductoService;
import com.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream =  req.getInputStream(); // aquí obtendría el json para luego convertirlo a un objeto que pueda trabajar
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(jsonStream, Producto.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Detalle Producto</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Detalle Producto!</h1>");
            out.println("       <ul>");
            out.println("           <li>Id: " + producto.getId() + "</li>");
            out.println("           <li>Nombre: " + producto.getNombre() + "</li>");
            out.println("           <li>Tipo: " + producto.getTipo() + "</li>");
            out.println("           <li>Precio: " + producto.getPrecio() + "</li>");
            out.println("       <ul>");
            out.println("   </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.getProductos();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);


    }
}
