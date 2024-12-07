package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Services.ServiceEmpleado;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-pizzeria")
public class AdminEmpleadoController {

    @Autowired
    private ServiceEmpleado serviceEmpleado;

    // Metodo para mostrar los empleados, formulario de agregar nuevo empleado y formulario para actualizar empleado actual
    @GetMapping("/gestion-personal")
    public String mostrarEmpleados(HttpSession session, Model model) {

        if (session.getAttribute("empleado") == null) {
            return "redirect:/admin-pizzeria/login";
        }

        // Si hay un empleado en la sesión, muestra la lista de empleados
        List<Empleado> empleados = serviceEmpleado.mostrarEmpleados();
        model.addAttribute("empleados", empleados);
        model.addAttribute("empleado", new Empleado());  // Para el formulario de nuevo empleado
        return "adminPersonal";  // Muestra la página de empleados
    }

    // Metodo para actualizar un empleado
    @PostMapping("/actualizar-empleado")
    public String actualizarEmpleado(@ModelAttribute("empleado") Empleado empleado, HttpSession session, Model model) {
        boolean success = serviceEmpleado.actualizarEmpleadoActual(empleado);
        if (success) {
            model.addAttribute("message", "Empleado actualizado exitosamente!");
        } else {
            model.addAttribute("message", "Error al actualizar el empleado.");
        }
        return mostrarEmpleados(session, model);  // Pasa ambos parámetros: session y model
    }

    // Metodo para añadir un nuevo empleado
    @PostMapping("/nuevo-empleado")
    public String anadirNuevoEmpleado(@ModelAttribute("empleado") Empleado empleado, HttpSession session, Model model) {
        boolean success = serviceEmpleado.anadirNuevoEmpleado(empleado);
        if (success) {
            model.addAttribute("message", "Empleado creado exitosamente!");
        } else {
            model.addAttribute("message", "Error al crear el empleado.");
        }
        return mostrarEmpleados(session, model);  // Pasa ambos parámetros: session y model
    }
}
