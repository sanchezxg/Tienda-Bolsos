package com.tiendabolsos.service;

import com.tiendabolsos.Repositorios.repoUsuarios;
import com.tiendabolsos.Repositorios.repo_Rol;
import com.tiendabolsos.model.rol;
import com.tiendabolsos.model.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private repoUsuarios repoUsuarios;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private repo_Rol repoRol;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String Guardar(usuario u){

      if (!repoUsuarios.existsByCorreo(u.getCorreo())){

          u.setContraseña(
                  passwordEncoder.encode(u.getContraseña())
          );
          rol rolUser = repoRol.findByNombre("USER")
                  .orElseThrow();

          u.getRoles().add(rolUser);

          repoUsuarios.save(u);

          return"usuario creado con exito";
      }else{

          return"ya existe una cuenta con este correo";
      }

    }


    public String ValidarCredenciales(usuario u){
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            u.getCorreo(),
                            u.getContraseña()
                    )
            );

            return "login exitoso";

        } catch (Exception e) {
            return "credenciales incorrectas";
        }


    }
public usuario BuscarUsuarioPorid(Integer id){

        return repoUsuarios.getReferenceById(id);
}
}
