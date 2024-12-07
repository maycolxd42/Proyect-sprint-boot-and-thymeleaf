package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Cliente;
import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Services.ServiceCliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin-pizzeria")
public class AdminClientesController {

    @Autowired
    private ServiceCliente serviceCliente;

    @GetMapping("/gestion-clientes")
    public String mostrarClientes(HttpSession session, Model model) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/admin-pizzeria/login";
        }

        List<Cliente> clientes = serviceCliente.obtenerClientes();
        model.addAttribute("clientes", clientes);
        return "adminClientes";
    }
}
