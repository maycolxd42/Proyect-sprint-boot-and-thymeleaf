package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.DetallePedido;
import java.util.List;

public interface IDetallePedido {

    List<DetallePedido> listarDetallesPorPedido(int idPedido);

    DetallePedido listarPorId(int id);

    int crear(DetallePedido detallePedido);

    int modificar(DetallePedido detallePedido);

    int eliminar(int id);
}
