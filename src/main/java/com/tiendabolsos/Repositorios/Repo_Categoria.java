package com.tiendabolsos.Repositorios;

import com.tiendabolsos.model.CategoriaProducto;
import com.tiendabolsos.model.rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Repo_Categoria extends JpaRepository<CategoriaProducto, Integer> {
}
