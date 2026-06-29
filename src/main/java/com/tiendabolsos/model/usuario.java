package com.tiendabolsos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor   

public class usuario {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)// que se genere automaticamente
     @Column(name = "id_usuario")
private int id_usuario;
     @Column(name = "nombre")
private String nombre;
      @Column(name = "correo")
private String correo;
     @Column(name = "contraseña")
private String contraseña;


    public usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rol_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<rol> roles = new HashSet<>();

}
