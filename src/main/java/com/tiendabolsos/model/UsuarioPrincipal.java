package com.tiendabolsos.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UsuarioPrincipal implements UserDetails {

    private final usuario usuario;

    public UsuarioPrincipal(usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=usuario.getRoles().
                stream().map(rol -> new SimpleGrantedAuthority("ROLE_"+rol.getNombre())).toList();
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getContraseña();
    }

    @Override
    public String getUsername() {
        return usuario.getCorreo();
    }

    public int getId() {
        return usuario.getIdUsuario();
    }

    // Métodos propios
    public String getNombre() {
        return usuario.getNombre();
    }

    public usuario getUsuario() {
        return usuario;
    }

    public Set<rol> getRoles() {
        return usuario.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

