package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Transaccion;
import com.ed.ecommerce.mvcDemo.Repository.ITransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTransaccion {

    @Autowired
    private ITransaccion iTransaccionRepository;

    // Listar todas las transacciones
    public List<Transaccion> listarTransacciones() {
        return iTransaccionRepository.listarTransacciones();
    }

    // Listar transacción por ID
    public Transaccion listarPorId(int idTransaccion) {
        return iTransaccionRepository.listarPorId(idTransaccion);
    }

    // Obtener transacción por ID de Pedido
    public Transaccion obtenerTransaccionPorIdPedido(int idPedido) {
        return iTransaccionRepository.obtenerTransaccionPorIdPedido(idPedido);
    }

    // Crear una nueva transacción
    public int crearTransaccion(Transaccion transaccion) {
        return iTransaccionRepository.crear(transaccion);
    }

    // Modificar una transacción existente
    public int modificarTransaccion(Transaccion transaccion) {
        return iTransaccionRepository.modificar(transaccion);
    }

    // Eliminar una transacción por ID
    public int eliminarTransaccion(int idTransaccion) {
        return iTransaccionRepository.eliminar(idTransaccion);
    }
}
