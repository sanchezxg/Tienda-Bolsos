package com.tiendabolsos.Repositorios;

import com.tiendabolsos.model.PasswordResetToken;
import com.tiendabolsos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_token extends JpaRepository<PasswordResetToken, Integer> {
    public PasswordResetToken findByToken(String token);


}
