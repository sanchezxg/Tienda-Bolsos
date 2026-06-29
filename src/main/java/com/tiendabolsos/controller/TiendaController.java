package com.tiendabolsos.controller;

import com.tiendabolsos.Repositorios.Repo_Compra;
import com.tiendabolsos.model.CarritoItem;
import com.tiendabolsos.model.Product;
import com.tiendabolsos.model.UsuarioPrincipal;
import com.tiendabolsos.service.CategoriaService;
import com.tiendabolsos.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/Tienda")
public class TiendaController {



    @Autowired
    private ProductService productoServicee;
    @Autowired
    private  CategoriaService categoriaService;




    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("productos", productoServicee.listarDestacados());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(@RequestParam(required = false) String categoria, Model model) {
        List<Product> productos;
        if (categoria != null && !categoria.isBlank()) {
            productos = productoServicee.buscarPorCategoria(categoria);
            model.addAttribute("categoriaActual", categoria);
        } else {
            productos = productoServicee.listarTodos();
        }
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "catalogo";
    }

    @GetMapping("/producto/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        Optional<Product> producto = productoServicee.BuscarPorID(id);
        if (producto.isEmpty()) {
            return "redirect:/catalogo";
        }
        model.addAttribute("producto", producto.get());
        model.addAttribute("relacionados", productoServicee.listarDestacados());
        return "detalle";
    }

    @GetMapping("/carrito")
    public String carrito(HttpSession session, Model model) {
        List<CarritoItem> items = obtenerCarrito(session);
        model.addAttribute("items", items);
        model.addAttribute("total", calcularTotal(items));
        return "carrito";
    }

    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam Integer idProducto,
                                   @RequestParam(defaultValue = "1") int cantidad,
                                   HttpSession session) {
        Optional<Product> producto = productoServicee.BuscarPorID(idProducto);
        if (producto.isPresent()) {
            List<CarritoItem> items = obtenerCarrito(session);
            Optional<CarritoItem> existente = items.stream()
                    .filter(i -> i.getProducto().getIdProducto().equals(idProducto))
                    .findFirst();
            if (existente.isPresent()) {
                existente.get().setCantidad(existente.get().getCantidad() + cantidad);
            } else {
                items.add(new CarritoItem(producto.get(), cantidad));
            }
            session.setAttribute("carrito", items);
        }
        return "redirect:/Tienda/carrito";
    }

    @PostMapping("/carrito/actualizar")
    public String actualizarCarrito(@RequestParam Long productoId,
                                    @RequestParam int cantidad,
                                    HttpSession session) {
        List<CarritoItem> items = obtenerCarrito(session);
        items.stream()
                .filter(i -> i.getProducto().getIdProducto().equals(productoId))
                .findFirst()
                .ifPresent(item -> item.setCantidad(cantidad));
        session.setAttribute("carrito", items);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar")
    public String eliminarDelCarrito(@RequestParam Long productoId,
                                     HttpSession session) {
        List<CarritoItem> items = obtenerCarrito(session);
        items.removeIf(i -> i.getProducto().getIdProducto().equals(productoId));
        session.setAttribute("carrito", items);
        return "redirect:/carrito";
    }


    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    @PostMapping("/contacto")
    public String enviarContacto(@RequestParam String nombre,
                                 @RequestParam String email,
                                 @RequestParam String mensaje,
                                 Model model) {
        model.addAttribute("exito", true);
        return "contacto";
    }

    @SuppressWarnings("unchecked")
    private List<CarritoItem> obtenerCarrito(HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }

    private BigDecimal  calcularTotal(List<CarritoItem> items) {
        return items.stream()
                .map(CarritoItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @GetMapping("/registro")
   public String registrar(){

    return "registro";
   }

     @GetMapping("/sesion")
   public String iniciarsesion(){

    return "sesion";
   }
    
}
