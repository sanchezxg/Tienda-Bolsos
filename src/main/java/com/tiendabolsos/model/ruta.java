package com.tiendabolsos.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
private int id_ruta;
    @Column(name = "nombre")
private String nombre;
    @Column(name = "url")
    private String url;




}
