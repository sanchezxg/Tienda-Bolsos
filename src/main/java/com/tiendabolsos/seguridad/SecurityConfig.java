package com.tiendabolsos.seguridad;
import com.tiendabolsos.service.UsuarioDetalle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private  final UsuarioDetalle usuarioService;

    public SecurityConfig(UsuarioDetalle usuarioService){

        this.usuarioService=usuarioService;
    }

    @Bean
    public PasswordEncoder codificaPass(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager autenticacion(AuthenticationConfiguration authConfig)
        throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http, AuthenticationManager authmanager)
            throws Exception{
             http.
                csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/usuarios/**").permitAll()
                        .requestMatchers("/Tienda/carrito/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/Tienda/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                ).authenticationManager(authmanager)
                     .userDetailsService(usuarioService)
                     .formLogin(form-> form
                             .loginPage("/usuarios/login")   // 👈 tu página personalizada
                             .loginProcessingUrl("/login")// 👈 donde se envía el POST del login
                             .defaultSuccessUrl("/Tienda/home", true)
                             .permitAll())
                     .httpBasic(basic->{});

                    return http.build();
    }


}
