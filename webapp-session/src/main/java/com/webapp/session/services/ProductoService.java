package com.webapp.session.services;

import com.webapp.session.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getProductos();
    Optional<Producto> findById(Long id);
}
