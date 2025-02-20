package com.webapp.session.controllers;

import com.webapp.session.models.Carro;
import com.webapp.session.models.ItemCarro;
import com.webapp.session.models.Producto;
import com.webapp.session.services.ProductoService;
import com.webapp.session.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoService productoService = new ProductoServiceImpl();
        Optional<Producto> producto = productoService.findById(id);

        if (producto.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, producto.get());
            Carro carro;
            HttpSession session = req.getSession();
            carro = (Carro) session.getAttribute("carro");
            carro.addItem(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}
