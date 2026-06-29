package com.tiendabolsos.controller;

import com.tiendabolsos.model.usuario;
import com.tiendabolsos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService Aservice;

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

}
