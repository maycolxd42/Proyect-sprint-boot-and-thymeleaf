package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Inventario;

import java.util.List;

public interface IInventario {

    List<Inventario> Listar();  // Listar todos los inventarios

    int crear(Inventario inventario);  // Crear un nuevo inventario

    int modificar(Inventario inventario);  // Modificar un inventario existente

    int eliminar(int id);  // Eliminar un inventario por id
}