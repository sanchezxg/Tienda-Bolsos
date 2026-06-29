package com.tiendabolsos.model;

import java.math.BigDecimal;

public class CarritoItem {

    private Product producto;
    private int cantidad;

    public CarritoItem() {}

    public CarritoItem(Product producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Product getProducto() { return producto; }
    public void setProducto(Product producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }
}
