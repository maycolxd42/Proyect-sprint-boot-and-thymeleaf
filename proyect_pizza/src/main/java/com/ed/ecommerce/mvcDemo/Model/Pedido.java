package com.ed.ecommerce.mvcDemo.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Pedido {

    private int idPedido;
    private int idCliente;
    private LocalDateTime fechaPedido;
    private double total; // Cambiado de BigDecimal a double
    private String estado;
    private String fechaPedidoFormateada;

    // Constructor vacío
    public Pedido() {
    }

    // Constructor
    public Pedido(int idPedido, int idCliente, LocalDateTime fechaPedido, double total, String estado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaPedidoFormateada() {
        return fechaPedidoFormateada;
    }

    public void setFechaPedidoFormateada(String fechaPedidoFormateada) {
        this.fechaPedidoFormateada = fechaPedidoFormateada;
    }
}
