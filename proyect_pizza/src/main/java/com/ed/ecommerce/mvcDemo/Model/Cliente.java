package com.ed.ecommerce.mvcDemo.Model;

import java.util.Date;

public class Cliente {

    private int idCliente;
    private String nombreCompleto;
    private String email;
    private String contrasena;
    private String telefono;
    private Date fechaNacimiento;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor
    public Cliente(String nombreCompleto, String email, String contrasena, String telefono, Date fechaNacimiento) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(int idCliente, String nombreCompleto, String email, String contrasena, String telefono, Date fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
