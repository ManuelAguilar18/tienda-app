package com.tienda.service.impl;

import com.tienda.entity.Producto;
import com.tienda.repository.ProductoRepository;
import com.tienda.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    public Producto actualizar(Long id, Producto nuevo) {
        Producto actual = obtenerPorId(id);
        actual.setNombre(nuevo.getNombre());
        actual.setDescripcion(nuevo.getDescripcion());
        actual.setPrecio(nuevo.getPrecio());
        actual.setStock(nuevo.getStock());
        return repo.save(actual);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
