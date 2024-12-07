package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Producto;
import com.ed.ecommerce.mvcDemo.Repository.IProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProducto {

    @Autowired
    private IProducto iProductoRepository;

    // Listar todos los productos
    public List<Producto> listarProductos() {
        return iProductoRepository.ListarProductos();
    }

    // Listar productos por categoría
    public List<Producto> listarPorCategoria(int idCategoriaProducto) {
        return iProductoRepository.ListarPorCategoria(idCategoriaProducto);
    }

    // Listar producto por ID
    public Producto listarPorId(int idProducto) {
        return iProductoRepository.ListarPorId(idProducto);
    }

    // Obtener producto por ID
    public Producto obtenerProductoPorId(int idProducto) {
        return iProductoRepository.obtenerProductoPorId(idProducto);
    }

    // Crear un nuevo producto
    public int crearProducto(Producto producto) {
        return iProductoRepository.crear(producto);
    }

    // Modificar un producto existente
    public int modificarProducto(Producto producto) {
        return iProductoRepository.modificar(producto);
    }

    // Eliminar un producto por ID
    public int eliminarProducto(int idProducto) {
        return iProductoRepository.eliminar(idProducto);
    }

}

