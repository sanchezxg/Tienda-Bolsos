package com.tiendabolsos.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendabolsos.model.usuario;

import java.util.Optional;

@Repository
public interface repoUsuarios extends JpaRepository<usuario, Integer> {

    boolean existsByCorreo(String correo);


    Optional<usuario> findByCorreo(String correo);
}
