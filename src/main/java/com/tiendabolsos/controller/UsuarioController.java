package com.tiendabolsos.controller;

import com.tiendabolsos.model.PasswordResetToken;
import com.tiendabolsos.model.usuario;
import com.tiendabolsos.service.ServiceToken;
import com.tiendabolsos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService Aservice;
@Autowired
    ServiceToken serviceToken;
    @Autowired
    private JavaMailSender mailSender;




    @GetMapping("/registro")
    public String vistaRegistro() {
        return "registro";

    }


    @PostMapping("/guardarRegistro")
    public String save(@ModelAttribute usuario u, Model model) {

        String mensaje = Aservice.Guardar(u);

        model.addAttribute("mensaje", mensaje);
        return "registro";
    }
    @GetMapping("/login")
    public String Logear(){
        return "sesion";}

    @GetMapping("/recuperar-contrasena")
    public String recuperarContraseña() {
        return "recuperar-contrasena";}


    @PostMapping("/enviar-recuperacion")
    public String RecuperarContraseña(@RequestParam String correo, RedirectAttributes redirectAttributes){
        Optional<usuario>  usuario=Aservice.BuscarPorCorreo(correo);
        if (!usuario.isPresent()) {
            redirectAttributes.addFlashAttribute("error","usuario no encontrado");
return "redirect:/usuarios/recuperar-contrasena";

        }else{

            String token = UUID.randomUUID().toString();
            PasswordResetToken t=new PasswordResetToken();
            t.setToken(token);
            t.setUsuario(usuario.get());
            t.setFechaExpiracion(
                    LocalDateTime.now().plusMinutes(30));
            serviceToken.Guardar(t);
            SimpleMailMessage mensaje = new SimpleMailMessage();

            mensaje.setTo(usuario.get().getCorreo());

            mensaje.setSubject("Recuperar contraseña");

            mensaje.setText(
                    "http://localhost:8080/usuarios/reset-password?token="
                            + token);

            mailSender.send(mensaje);
            redirectAttributes.addFlashAttribute("success","Email enviado");
            return "redirect:/usuarios/recuperar-contrasena";

        }



    }

    @GetMapping("/reset-password")
    public String formulario(
            @RequestParam String token,
            Model model){

        PasswordResetToken t =serviceToken.BuscarPortoken(token);
        if (t==null){

return "error";

        }else{


            model.addAttribute("token", token);

            return "reset-password";
        }

    }


@PostMapping("/reset-password")
    public String cambiarPassword(@RequestParam String password, @RequestParam String token){

        PasswordResetToken t=serviceToken.BuscarPortoken(token);
        usuario usuario=t.getUsuario();

       String mensaje= Aservice.CambiarContrasena(usuario,password);
       serviceToken.elimnar(t);
return "redirect:/login";

}


}
