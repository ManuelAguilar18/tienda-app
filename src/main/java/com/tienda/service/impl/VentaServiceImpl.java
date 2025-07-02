package com.tienda.service.impl;

import com.tienda.entity.Producto;
import com.tienda.entity.ProductoVenta;
import com.tienda.entity.Venta;
import com.tienda.repository.ProductoRepository;
import com.tienda.repository.VentaRepository;
import com.tienda.service.VentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepo;
    private final ProductoRepository productoRepo;

    public VentaServiceImpl(VentaRepository ventaRepo, ProductoRepository productoRepo) {
        this.ventaRepo = ventaRepo;
        this.productoRepo = productoRepo;
    }

    @Transactional
    public Venta registrarVenta(List<ProductoVenta> detalles) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);

        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setProductos(new ArrayList<>());
        BigDecimal total = BigDecimal.ZERO;

        for (ProductoVenta pv : detalles) {
            Producto prod = productoRepo.findById(pv.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (prod.getStock() < pv.getCantidad())
                throw new IllegalArgumentException("Stock insuficiente para " + prod.getNombre());

            prod.setStock(prod.getStock() - pv.getCantidad());
            productoRepo.save(prod);

            BigDecimal subtotal = prod.getPrecio().multiply(BigDecimal.valueOf(pv.getCantidad()));

            pv.setProducto(prod);
            pv.setVenta(venta);
            pv.setSubtotal(subtotal);

            venta.getProductos().add(pv);
            total = total.add(subtotal);
        }

        venta.setTotal(total);
        return ventaRepo.save(venta);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepo.findAll();
    }
}
