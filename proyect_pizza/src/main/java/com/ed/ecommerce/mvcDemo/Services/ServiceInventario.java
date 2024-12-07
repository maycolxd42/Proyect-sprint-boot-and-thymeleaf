package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Inventario;
import com.ed.ecommerce.mvcDemo.Repository.IInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInventario {

    @Autowired
    private IInventario inventarioRepository;

    // Listar todos los inventarios
    public List<Inventario> listarInventarios() {
        return inventarioRepository.Listar();
    }

    // Crear un nuevo inventario
    public int crearInventario(Inventario inventario) {
        return inventarioRepository.crear(inventario);
    }

    // Modificar un inventario existente
    public int modificarInventario(Inventario inventario) {
        return inventarioRepository.modificar(inventario);
    }

    // Eliminar un inventario por ID
    public int eliminarInventario(int id) {
        return inventarioRepository.eliminar(id);
    }
}
