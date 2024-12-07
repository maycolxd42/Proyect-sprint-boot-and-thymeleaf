package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Pedido;
import java.util.List;

public interface IPedido {

    List<Pedido> listarPedidos();

    Pedido listarPorId(int id);

    int crear(Pedido pedido);

    int modificar(Pedido pedido);

    int eliminar(int id);
}
