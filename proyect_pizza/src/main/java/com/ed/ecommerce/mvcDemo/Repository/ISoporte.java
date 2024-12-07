package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Soporte;

import java.util.List;

public interface ISoporte {

    int guardar(Soporte soporte);
    List<Soporte> obtenerTodos();
    Soporte obtenerPorId(int idSoporte); // Nuevo método
    int eliminarPorId(int idSoporte);   // Método para borrar
    public void enviarRespuesta(String to, String subject, String body);

}
