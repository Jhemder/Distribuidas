package com.example.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "entrada" o "salida"
    private Integer cantidad;
    private String motivo;
    private LocalDateTime fecha;
    private String usuarioResponsable;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
