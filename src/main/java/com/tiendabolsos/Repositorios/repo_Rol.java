package com.tiendabolsos.Repositorios;

import com.tiendabolsos.model.rol;
import com.tiendabolsos.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface repo_Rol extends JpaRepository<rol, Integer> {

    Optional<rol> findByNombre(String nombre);
}
