package com.tienda.service;

import com.tienda.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listar();

    public Producto obtenerPorId(Long id);

    public Producto guardar(Producto producto);

    public Producto actualizar(Long id, Producto nuevo);

    public void eliminar(Long id);
}
