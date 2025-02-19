package com.webapp.headers.controllers;

import com.webapp.headers.models.Carro;
import com.webapp.headers.models.ItemCarro;
import com.webapp.headers.models.Producto;
import com.webapp.headers.services.ProductoService;
import com.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

@WebServlet("/actualizar-carro")
public class ActualizarCarroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Carro carro = (Carro) session.getAttribute("carro");

        Enumeration<String> paramNames = req.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith("cantidad_")) {
                Long idProducto = Long.parseLong(paramName.substring(9));
                Integer cantidad = Integer.valueOf(req.getParameter(paramName));
                Optional<ItemCarro> optionalItem = carro.getItems().stream()
                        .filter(productoItem -> productoItem.getProducto().getId().equals(idProducto))
                        .findAny();
                optionalItem.ifPresent(itemCarro -> carro.actualizarItem(itemCarro, cantidad));
            }
        }

        Long idProductoAEliminar = Long.parseLong(req.getParameter("eliminarProductoCheckbox") != null ? req.getParameter("eliminarProductoCheckbox") : "-1");

        if (idProductoAEliminar != -1L) {
            carro.eliminarItem(idProductoAEliminar);
        }

        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}
