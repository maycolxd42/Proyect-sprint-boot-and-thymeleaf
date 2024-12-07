package com.ed.ecommerce.mvcDemo.Model;

public class Inventario {

    private int idInventario;
    private int idProducto;
    private int cantidadDisponible;

    // Constructor vacío
    public Inventario() {
    }

    // Constructor con todos los atributos
    public Inventario(int idInventario, int idProducto, int cantidadDisponible) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.cantidadDisponible = cantidadDisponible;
    }

    // Getters y Setters

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
