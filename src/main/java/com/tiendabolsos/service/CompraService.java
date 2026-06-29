package com.tiendabolsos.service;


import com.tiendabolsos.Repositorios.Repo_Compra;
import com.tiendabolsos.Repositorios.repoUsuarios;
import com.tiendabolsos.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {
    @Autowired
    private Repo_Compra repoCompra;


}
