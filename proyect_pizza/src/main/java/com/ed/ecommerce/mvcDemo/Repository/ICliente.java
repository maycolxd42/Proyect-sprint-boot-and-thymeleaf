package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Cliente;

import java.util.List;

public interface ICliente {
    Cliente validarCliente(String email, String password);
    boolean registrar(Cliente cliente);
    List<Cliente> obtenerClientes();
}