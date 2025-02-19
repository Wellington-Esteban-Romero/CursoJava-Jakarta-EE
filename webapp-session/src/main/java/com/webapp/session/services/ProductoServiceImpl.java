package com.webapp.session.services;

import com.webapp.session.models.Producto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> getProductos() {
        return List.of(new Producto(1L, "notebook", "computación", 2000),
                new Producto(2L, "escritorio", "oficina", 100),
                new Producto(3L, "mouse", "computación", 60));
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return getProductos().stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }
}
