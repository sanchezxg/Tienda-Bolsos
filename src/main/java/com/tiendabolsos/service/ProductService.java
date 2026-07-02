package com.tiendabolsos.service;

import com.tiendabolsos.Repositorios.Repo_Producto;
import com.tiendabolsos.Repositorios.repoUsuarios;
import com.tiendabolsos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private Repo_Producto repoProducto;

public List<Product> buscarPorCategoria(String categoria){

    return repoProducto.findByCategoria_Nombre(categoria);
}

    public List<Product>  listarTodos(){

        return repoProducto.findAll();
    }

public Optional<Product> BuscarPorID(Integer id){

    return repoProducto.findById(id);
}

public List<Product> listarDestacados(){

    return repoProducto.findAll().stream().limit(4).collect(Collectors.toList());

}
public Product obtenerPorID(int id){

    return repoProducto.getReferenceById(id);
}
public List<Product> obtenerProductosUsarioPorID(Integer id){

    return  repoProducto.findByUsuarioIdUsuario(id);
}

public void EliminarProducto(Integer Id){

    repoProducto.deleteById(Id);
}

public void GuardarProducto(Product product){

    repoProducto.save(product);
}
}
