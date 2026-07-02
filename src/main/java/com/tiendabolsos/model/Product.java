package com.tiendabolsos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(nullable = false, length = 100)

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "la descripcion es obligatorio")
    @Column(length = 255)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe se mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;


@Positive(message = "stock debe se mayor a 0")
@NotNull(message = "El stock es obligatorio")
    @Column(nullable = false)
    private Integer stock;

    @NotBlank(message = "La imagen es obligatorio")
    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaProducto categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private usuario usuario;


}
