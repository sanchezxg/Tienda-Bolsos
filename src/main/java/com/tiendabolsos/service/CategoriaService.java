package com.tiendabolsos.service;

import com.tiendabolsos.Repositorios.Repo_Categoria;
import com.tiendabolsos.Repositorios.repoUsuarios;
import com.tiendabolsos.model.CategoriaProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private Repo_Categoria repoCategoria;

public List<CategoriaProducto> listarCategorias(){

    return repoCategoria.findAll();

}

public CategoriaProducto BuscarCategoriaPorID(int id){
    return repoCategoria.getReferenceById(id);

}
}
