package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.DetallePedido;
import com.ed.ecommerce.mvcDemo.Repository.IDetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDetallePedido {

    @Autowired
    private IDetallePedido iDetallePedidoRepository;

    // Listar detalles de un pedido
    public List<DetallePedido> listarDetallesPorPedido(int idPedido) {
        return iDetallePedidoRepository.listarDetallesPorPedido(idPedido);
    }

    // Listar detalle por ID
    public DetallePedido listarPorId(int idDetallePedido) {
        return iDetallePedidoRepository.listarPorId(idDetallePedido);
    }

    // Crear un nuevo detalle de pedido
    public int crearDetallePedido(DetallePedido detallePedido) {
        return iDetallePedidoRepository.crear(detallePedido);
    }

    // Modificar un detalle de pedido existente
    public int modificarDetallePedido(DetallePedido detallePedido) {
        return iDetallePedidoRepository.modificar(detallePedido);
    }

    // Eliminar un detalle de pedido por ID
    public int eliminarDetallePedido(int idDetallePedido) {
        return iDetallePedidoRepository.eliminar(idDetallePedido);
    }
}
