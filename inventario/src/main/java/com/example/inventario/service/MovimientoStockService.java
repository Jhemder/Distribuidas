package com.example.inventario.service;

import com.example.inventario.model.MovimientoStock;
import com.example.inventario.model.Producto;
import com.example.inventario.repository.MovimientoStockRepository;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimientoStockService {

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public MovimientoStock registrarMovimiento(MovimientoStock movimiento) {
        Producto producto = productoRepository.findById(movimiento.getProducto().getId()).orElse(null);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        if (movimiento.getTipo().equalsIgnoreCase("entrada")) {
            producto.setCantidadDisponible(producto.getCantidadDisponible() + movimiento.getCantidad());
        } else if (movimiento.getTipo().equalsIgnoreCase("salida")) {
            if (producto.getCantidadDisponible() < movimiento.getCantidad()) {
                throw new RuntimeException("Stock insuficiente");
            }
            producto.setCantidadDisponible(producto.getCantidadDisponible() - movimiento.getCantidad());
        } else {
            throw new RuntimeException("Tipo de movimiento no válido");
        }

        productoRepository.save(producto);

        movimiento.setFecha(LocalDateTime.now());
        return movimientoStockRepository.save(movimiento);
    }
}
