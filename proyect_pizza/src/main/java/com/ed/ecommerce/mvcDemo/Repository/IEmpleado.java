package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Empleado;

import java.util.List;

public interface IEmpleado {

    // Metodo para validar el login de un empleado
    Empleado validarEmpleado(String email, String contrasena);

    // Metodo para mostrar todos los empleados
    List<Empleado> mostrarEmpleados();

    // Metodo para actualizar los datos del empleado actual
    boolean actualizarEmpleadoActual(Empleado empleado);

    // Metodo para añadir un nuevo empleado
    boolean anadirNuevoEmpleado(Empleado empleado);
}
