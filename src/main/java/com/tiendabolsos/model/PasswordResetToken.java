package com.tiendabolsos.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_token")
@Data
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime fechaExpiracion;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private usuario usuario;
}
