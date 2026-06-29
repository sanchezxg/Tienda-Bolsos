package com.tiendabolsos.Repositorios;

import com.tiendabolsos.model.CategoriaProducto;
import com.tiendabolsos.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repo_DetalleCompra extends JpaRepository<DetalleCompra, Integer> {
    List<DetalleCompra> findByCompraIdCompra(Integer idCompra);
}
