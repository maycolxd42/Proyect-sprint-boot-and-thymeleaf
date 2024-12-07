package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Pedido;
import com.ed.ecommerce.mvcDemo.Repository.IPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePedido {

    @Autowired
    private IPedido iPedidoRepository;

    // Listar todos los pedidos
    public List<Pedido> listarPedidos() {
        return iPedidoRepository.listarPedidos();
    }

    // Listar pedido por ID
    public Pedido listarPorId(int idPedido) {
        return iPedidoRepository.listarPorId(idPedido);
    }

    // Crear un nuevo pedido
    public int crearPedido(Pedido pedido) {
        return iPedidoRepository.crear(pedido);
    }

    // Modificar un pedido existente
    public int modificarPedido(Pedido pedido) {
        return iPedidoRepository.modificar(pedido);
    }

    // Eliminar un pedido por ID
    public int eliminarPedido(int idPedido) {
        return iPedidoRepository.eliminar(idPedido);
    }
}
