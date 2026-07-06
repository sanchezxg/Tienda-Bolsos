package com.tiendabolsos.controller;

import com.tiendabolsos.model.CategoriaProducto;
import com.tiendabolsos.model.Product;
import com.tiendabolsos.model.UsuarioPrincipal;
import com.tiendabolsos.model.usuario;
import com.tiendabolsos.service.CategoriaService;
import com.tiendabolsos.service.ProductService;
import com.tiendabolsos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductosController {
    @Autowired
    private ProductService productoServicee;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    UsuarioService Aservice;

    @GetMapping("/productos")
    public String gestionarProductos(Model model, @AuthenticationPrincipal UsuarioPrincipal usuarioLogueado) {

        List<Product> products = productoServicee.obtenerProductosUsarioPorID(usuarioLogueado.getId());
        model.addAttribute("productos", products);

        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("product", new Product());
        return "productos";

    }

    @PostMapping("/productos/eliminar/{idProducto}")
    public String EliminarProducto(@PathVariable Integer idProducto) {

        productoServicee.EliminarProducto(idProducto);
        return "redirect:/productos";
    }

    @PostMapping ("/productos/guardar")

    public String GuardarProductos(@AuthenticationPrincipal UsuarioPrincipal usuarioPrincipal
            , @Valid @ModelAttribute Product producto,BindingResult result, @RequestParam int idCategoria
                                   ,  Model model, @AuthenticationPrincipal UsuarioPrincipal usuarioLogueado
    ){
        if (result.hasErrors()) {
            List<Product> products = productoServicee.obtenerProductosUsarioPorID(usuarioLogueado.getId());
            model.addAttribute("productos", products);

            model.addAttribute("categorias", categoriaService.listarCategorias());
           return "productos";
        }

        CategoriaProducto categoriaProducto=categoriaService.BuscarCategoriaPorID(idCategoria);
        usuario usuario=Aservice.BuscarUsuarioPorid(usuarioPrincipal.getId());
        producto.setCategoria(categoriaProducto);
        producto.setUsuario(usuario);

        productoServicee.GuardarProducto(producto);
return"redirect:/productos";
    }

    @GetMapping ("/productos/editar/{idProducto}")

    public String  EditarProducto(@PathVariable int idProducto,Model model){
        Product producto=productoServicee.obtenerPorID(idProducto);

        model.addAttribute("product",producto);
        model.addAttribute("categorias",categoriaService.listarCategorias());
        return "editar-producto";

    }

    @PostMapping("/productos/actualizar/{idProducto}")
        public String Actualizar(@Valid@ModelAttribute Product product,BindingResult result, RedirectAttributes redirectAttributes
    ,Model  model,@RequestParam int idCategoria,@AuthenticationPrincipal UsuarioPrincipal usuarioLogueado){
        if (result.hasErrors()) {
            Product producto=productoServicee.obtenerPorID(product.getIdProducto());

            model.addAttribute("product",producto);
            model.addAttribute("categorias",categoriaService.listarCategorias());
            return "editar-producto";

        }
        CategoriaProducto categoria=categoriaService.BuscarCategoriaPorID(idCategoria);
        usuario usuario=Aservice.BuscarUsuarioPorid(usuarioLogueado.getId());
        product.setUsuario(usuario);
        product.setCategoria(categoria);
productoServicee.GuardarProducto(product);
redirectAttributes.addFlashAttribute("success","cambio realizado");
return"redirect:/productos";

        }
}
