package com.ed.ecommerce.mvcDemo.Model;

import java.time.LocalDateTime;

public class Transaccion {

    private int idTransaccion;
    private int idpedido;
    private int idTipoDocumento;
    private String estado;
    private LocalDateTime fechaTransaccion;
    private String fechaTransaccionFormateada;

    // Constructor vacío
    public Transaccion() {
    }

    // Constructor
    public Transaccion(int idTransaccion, int idpedido, String estado, int idTipoDocumento, LocalDateTime fechaTransaccion) {
        this.idTransaccion = idTransaccion;
        this.idpedido = idpedido;
        this.estado = estado;
        this.idTipoDocumento = idTipoDocumento;
        this.fechaTransaccion = fechaTransaccion;
    }

    // Getters y Setters
    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdtipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getFechaTransaccionFormateada() {
        return fechaTransaccionFormateada;
    }

    public void setFechaTransaccionFormateada(String fechaTransaccionFormateada) {
        this.fechaTransaccionFormateada = fechaTransaccionFormateada;
    }

}
