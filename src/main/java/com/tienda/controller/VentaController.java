package com.tienda.controller;

import com.tienda.entity.Producto;
import com.tienda.entity.ProductoVenta;
import com.tienda.entity.Venta;
import com.tienda.service.ProductoService;
import com.tienda.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final ProductoService productoService;

    public VentaController(VentaService ventaService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @GetMapping
    public String formularioVenta(Model model) {
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("productosVenta", new ArrayList<ProductoVenta>());
        return "venta";
    }

    @PostMapping
    public String registrarVenta(@RequestParam List<Long> productoId,
                                 @RequestParam List<Integer> cantidad,
                                 Model model) {
        List<ProductoVenta> detalles = new ArrayList<>();

        for (int i = 0; i < productoId.size(); i++) {
            Producto producto = new Producto();
            producto.setId(productoId.get(i));

            ProductoVenta pv = new ProductoVenta();
            pv.setProducto(producto);
            pv.setCantidad(cantidad.get(i));
            detalles.add(pv);
        }

        try {
            Venta venta = ventaService.registrarVenta(detalles);
            model.addAttribute("venta", venta);
            return "redirect:/ventas/resumen/" + venta.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "venta";
        }
    }

    @GetMapping("/resumen/{id}")
    public String resumen(@PathVariable Long id, Model model) {
        Venta venta = ventaService.listarVentas()
                .stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow();
        model.addAttribute("venta", venta);
        return "resumen-venta";
    }

    @GetMapping("/todas")
    public String listarVentas(Model model) {
        model.addAttribute("listaVentas", ventaService.listarVentas());
        return "lista-ventas";
    }

}
