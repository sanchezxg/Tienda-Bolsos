package com.tiendabolsos.Repositorios;

import com.tiendabolsos.model.Product;
import com.tiendabolsos.model.rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_Producto extends JpaRepository<Product, Integer> {

    List<Product> findByCategoria_Nombre(String nombre);

    List<Product> findAll();
}
