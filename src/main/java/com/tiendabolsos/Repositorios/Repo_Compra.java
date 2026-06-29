package com.tiendabolsos.Repositorios;

import com.tiendabolsos.model.Compra;
import com.tiendabolsos.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_Compra extends JpaRepository<Compra, Integer> {
    List<Compra> findByUsuarioOrderByFechaDesc(usuario usuario);
}
