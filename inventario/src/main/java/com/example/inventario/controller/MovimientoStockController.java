package com.example.inventario.controller;

import com.example.inventario.model.MovimientoStock;
import com.example.inventario.service.MovimientoStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoStockController {

    @Autowired
    private MovimientoStockService movimientoStockService;

    @PostMapping
    public MovimientoStock registrarMovimiento(@RequestBody MovimientoStock movimiento) {
        return movimientoStockService.registrarMovimiento(movimiento);
    }
}
