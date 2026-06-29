package com.tiendabolsos.service;

import com.tiendabolsos.Repositorios.Repo_DetalleCompra;
import com.tiendabolsos.model.Compra;
import com.tiendabolsos.model.DetalleCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraService {
    @Autowired
    private Repo_DetalleCompra repoDetalleCompra;


public void guadar(DetalleCompra detalleCompra){

    repoDetalleCompra.save(detalleCompra);

}

public List<DetalleCompra> EncontrarDetallesPorID(Integer idCompra){

    return repoDetalleCompra.findByCompraIdCompra(idCompra);
}
}
