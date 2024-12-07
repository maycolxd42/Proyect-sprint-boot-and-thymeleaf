package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Soporte;
import com.ed.ecommerce.mvcDemo.Repository.ISoporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSoporte {

    @Autowired
    private ISoporte iSoporte;  // Inyección de la implementación del repositorio

    // Guardar un nuevo mensaje de soporte
    public int guardarSoporte(Soporte soporte) {
        return iSoporte.guardar(soporte);
    }

    // Obtener todos los mensajes de soporte
    public List<Soporte> obtenerTodos() {
        return iSoporte.obtenerTodos();
    }

    // Obtener un soporte por su ID
    public Soporte obtenerPorId(int idSoporte) {
        return iSoporte.obtenerPorId(idSoporte);
    }

    // Eliminar un soporte por su ID
    public int eliminarSoporte(int idSoporte) {
        return iSoporte.eliminarPorId(idSoporte);
    }

    public void enviarRespuesta(String to, String subject, String body) {
        iSoporte.enviarRespuesta(to, subject, body);
    }


}
