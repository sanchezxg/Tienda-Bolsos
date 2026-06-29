package com.tiendabolsos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int id_rol;
    @Column(name = "nombre")
    private String nombre;

@ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name="rol_ruta",
            joinColumns=@JoinColumn(name="id_rol"),
            inverseJoinColumns=@JoinColumn(name = "id_ruta")
    )
private Set<ruta> rutas= new HashSet<>();

}
