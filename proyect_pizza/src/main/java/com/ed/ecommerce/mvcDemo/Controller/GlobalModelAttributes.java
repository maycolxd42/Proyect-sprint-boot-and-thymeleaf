package com.ed.ecommerce.mvcDemo.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import com.ed.ecommerce.mvcDemo.Model.Cliente;
import com.ed.ecommerce.mvcDemo.Model.Empleado;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute
    public void agregarAtributosSesion(HttpSession session, Model model) {
        // Recuperar el cliente de la sesión
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
        }

        // Recuperar el empleado de la sesión
        Empleado empleado = (Empleado) session.getAttribute("empleado");
        if (empleado != null) {
            model.addAttribute("empleado", empleado);
        }
    }
}
