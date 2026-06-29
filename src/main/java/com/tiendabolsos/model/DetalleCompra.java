package com.tiendabolsos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
    @Table(name = "detalle_compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class DetalleCompra {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_detalle")
        private Integer idDetalle;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_compra", nullable = false)
        private Compra compra;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_producto", nullable = false)
        private Product producto;

        @Column(nullable = false)
        private Integer cantidad;

        @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
        private BigDecimal precioUnitario;

        @Column(nullable = false, precision = 10, scale = 2)
        private BigDecimal subtotal;


}
