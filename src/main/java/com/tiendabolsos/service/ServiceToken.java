package com.tiendabolsos.service;

import com.tiendabolsos.Repositorios.Repo_token;
import com.tiendabolsos.model.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceToken {
@Autowired
    Repo_token repoToken;
public void Guardar(PasswordResetToken token){
repoToken.save(token);
}

public PasswordResetToken BuscarPortoken(String token){

    return  repoToken.findByToken(token);
}

public void elimnar(PasswordResetToken token){

    repoToken.delete(token);
}
}
