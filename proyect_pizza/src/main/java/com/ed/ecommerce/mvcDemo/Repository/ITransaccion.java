package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Transaccion;
import java.util.List;

public interface ITransaccion {

    List<Transaccion> listarTransacciones();  // Listar todas las transacciones

    Transaccion listarPorId(int id);  // Listar transacción por ID

    Transaccion obtenerTransaccionPorIdPedido(int idPedido);  // Obtener transacción por ID de Pedido

    int crear(Transaccion transaccion);  // Crear una nueva transacción

    int modificar(Transaccion transaccion);  // Modificar una transacción existente

    int eliminar(int id);  // Eliminar una transacción por ID
}
