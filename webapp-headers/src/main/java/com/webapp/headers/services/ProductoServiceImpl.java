package com.webapp.headers.services;

import com.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> getProductos() {
        return List.of(new Producto(1L, "notebook", "computación", 2000),
                new Producto(2L, "escritorio", "oficina", 100),
                new Producto(3L, "mouse", "computación", 60));
    }

    @Override
    public Optional<Producto> buscarProducto(String nombre) {
        return getProductos().stream()
                .filter(p -> {
                    if (nombre == null || nombre.isBlank()) {
                        return false;
                    }
                    return p.getNombre().contains(nombre);
                }).findFirst();
    }
}
