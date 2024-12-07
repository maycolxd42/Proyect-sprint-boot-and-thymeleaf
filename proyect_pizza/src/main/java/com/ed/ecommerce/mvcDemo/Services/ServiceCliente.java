package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Cliente;
import com.ed.ecommerce.mvcDemo.Repository.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCliente {

    @Autowired
    private ICliente clienteRepo;

    // Validar el inicio de sesión del cliente
    public Cliente validarCliente(String email, String password) {
        return clienteRepo.validarCliente(email, password);
    }

    // Registrar un nuevo cliente
    public boolean registrarCliente(Cliente cliente) {
        return clienteRepo.registrar(cliente);
    }

    // Obtener listado de clientes
    public List<Cliente> obtenerClientes() {
        return clienteRepo.obtenerClientes();
    }
}
