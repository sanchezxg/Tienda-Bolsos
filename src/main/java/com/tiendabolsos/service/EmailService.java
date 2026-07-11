package com.tiendabolsos.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    public EmailService(
            @Value("${RESEND_API_KEY}") String apiKey
    ){

        this.resend = new Resend(apiKey);

    }


    public void enviarCorreo(String destino, String token) throws ResendException {

        CreateEmailOptions params =
                CreateEmailOptions.builder()
                        .from("onboarding@resend.dev")
                        .to(destino)
                        .subject("Recuperar contraseña")
                        .html(
                                "<h2>Recuperación de contraseña</h2>" +
                                        "<p>Haz clic aquí:</p>" +
                                        "<a href='https://tienda-bolsos.onrender.com/usuarios/reset-password?token="
                                        + token +
                                        "'>Cambiar contraseña</a>"
                        )
                        .build();


        resend.emails()
                .send(params);

    }
}