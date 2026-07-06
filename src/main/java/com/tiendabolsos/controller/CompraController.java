package com.tiendabolsos.controller;

import com.tiendabolsos.Repositorios.Repo_Compra;
import com.tiendabolsos.Repositorios.Repo_DetalleCompra;
import com.tiendabolsos.Repositorios.repoUsuarios;
import com.tiendabolsos.model.*;
import com.tiendabolsos.service.DetalleCompraService;
import com.tiendabolsos.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CompraController {

@Autowired
    private DetalleCompraService detalleCompraService;

    @Autowired
    private Repo_Compra repoCompra;  //

    @Autowired
    private repoUsuarios repoUsuarios;  //

    @Autowired
    private ProductService productService;


@PostMapping ("/ProcesarCompra")
@Transactional
    public String ProcesarCompra(HttpSession session, @AuthenticationPrincipal UsuarioPrincipal usuarioLogueado, @RequestParam BigDecimal total,

 RedirectAttributes redirectAttributes){

if (!validarStuck(session,redirectAttributes)){
    return "redirect:/Tienda/carrito";
}else {

    usuario usuario = repoUsuarios.findById(usuarioLogueado.getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    LocalDateTime ahora = LocalDateTime.now();
    Compra compra = new Compra();
    compra.setTotal(total);
    compra.setUsuario(usuario);
    compra.setFecha(ahora);

    repoCompra.save(compra);


    ProcesarDetalle(compra, session);

    session.removeAttribute("carrito");
    redirectAttributes.addFlashAttribute("success", "Compra realizada correctamente.");
    return "redirect:/Tienda/carrito";
}


}

public boolean validarStuck(HttpSession session
,RedirectAttributes redirectAttributes){

    List<CarritoItem> itms=obtenerCarrito(session);

  for (CarritoItem i: itms) {
      if (i.getProducto().getStock() < i.getCantidad()) {

          redirectAttributes.addFlashAttribute(
                  "error",
                  "No hay suficiente stock para " + i.getProducto().getNombre());
          return false;
      }
  }

return true;

}

public void ProcesarDetalle(Compra compra, HttpSession session
){

List<CarritoItem> itms=obtenerCarrito(session);
itms.stream().forEach(i-> { DetalleCompra detalle=new DetalleCompra();
    Product product=productService.obtenerPorID(i.getProducto().getIdProducto());

    product.setStock(product.getStock()-i.getCantidad());
    productService.GuardarProducto(product);
    detalle.setProducto(i.getProducto());
    detalle.setCompra(compra);
    detalle.setCantidad(i.getCantidad());
    detalle.setPrecioUnitario(i.getProducto().getPrecio());
    detalle.setSubtotal(i.getProducto().getPrecio().multiply(BigDecimal.valueOf(i.getCantidad())));
    detalleCompraService.guadar(detalle);


});

}

    @GetMapping("/compras")
    public String compras(@AuthenticationPrincipal UsuarioPrincipal principal, Model model) {
        if (principal == null) {
            return "redirect:/usuarios/login";
        }
        model.addAttribute("compras", repoCompra.findByUsuarioOrderByFechaDesc(principal.getUsuario()));
        return "compras";
    }


    private List<CarritoItem> obtenerCarrito(HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }


    @GetMapping("/detalles/{idCompra}")
public String DetallarCompra(@PathVariable Integer idCompra, Model model){
Compra compra=repoCompra.getReferenceById(idCompra);
model.addAttribute("compra",compra);
List<DetalleCompra> detalles= detalleCompraService.EncontrarDetallesPorID(compra.getIdCompra());
model.addAttribute("detalles",detalles);
    return "detalles";
}
}
