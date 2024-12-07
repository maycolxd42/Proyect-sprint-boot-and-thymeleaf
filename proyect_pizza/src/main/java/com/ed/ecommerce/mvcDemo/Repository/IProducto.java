package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Producto;
import java.util.List;

public interface IProducto {

    List<Producto> ListarProductos();

    Producto ListarPorId(int id);

    Producto obtenerProductoPorId(int idProducto);

    List<Producto> ListarPorCategoria(int idCategoriaProducto);

    int crear(Producto producto);

    int modificar(Producto producto);

    int eliminar(int id);
}
