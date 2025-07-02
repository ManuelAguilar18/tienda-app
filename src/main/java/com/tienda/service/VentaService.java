package com.tienda.service;

import com.tienda.entity.ProductoVenta;
import com.tienda.entity.Venta;

import java.util.List;

public interface VentaService {
    public Venta registrarVenta(List<ProductoVenta> detalles);
    public List<Venta> listarVentas();
}
