package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Repository.IEmpleadoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEmpleado {

    private IEmpleadoImpl empleadoImpl = new IEmpleadoImpl();

    // Metodo para validar un empleado con email y contrasena
    public Empleado validarEmpleado(String email, String contrasena) {
        return empleadoImpl.validarEmpleado(email, contrasena);
    }

    // Metodo para mostrar todos los empleados
    public List<Empleado> mostrarEmpleados() {
        return empleadoImpl.mostrarEmpleados();
    }

    // Metodo para actualizar los datos del empleado actual
    public boolean actualizarEmpleadoActual(Empleado empleado) {
        return empleadoImpl.actualizarEmpleadoActual(empleado);
    }

    // Metodo para anadir un nuevo empleado
    public boolean anadirNuevoEmpleado(Empleado empleado) {
        return empleadoImpl.anadirNuevoEmpleado(empleado);
    }
}
