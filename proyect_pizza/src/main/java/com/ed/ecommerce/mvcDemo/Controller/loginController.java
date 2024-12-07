package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Cliente;
import com.ed.ecommerce.mvcDemo.Services.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/pizzeria")
public class loginController {

    @Autowired
    private ServiceCliente serviceCliente;

    // Mostrar formulario de inicio de sesión
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    // Procesar inicio de sesión
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Cliente cliente = serviceCliente.validarCliente(email, password);
        if (cliente != null) {
            session.setAttribute("cliente", cliente);
            return "redirect:/pizzeria/index";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    // Procesar registro de clientes
    @PostMapping("/register")
    public String registrarCliente(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
            Model model) {

        Cliente nuevoCliente = new Cliente(fullName, email, password, phone, birthDate);
        boolean registroExitoso = serviceCliente.registrarCliente(nuevoCliente);

        if (registroExitoso) {
            model.addAttribute("success", "Registro exitoso. ¡Inicia sesión!");
            return "redirect:/pizzeria/login";
        } else {
            model.addAttribute("error", "El correo ya está registrado.");
            return "login";
        }
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/pizzeria/login";
    }
}
