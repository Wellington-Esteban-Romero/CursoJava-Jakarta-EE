package com.webapp.headers.services;

import com.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getProductos();
    Optional<Producto> buscarProducto(String nombre);
}
