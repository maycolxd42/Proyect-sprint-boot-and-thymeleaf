package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Services.ServiceEmpleado;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLoginController {

    @Autowired
    private ServiceEmpleado serviceEmpleado;

    @GetMapping("/admin-pizzeria/login")
    public String mostrarLogin(HttpSession session) {
        if (session.getAttribute("empleado") != null) {
            return "redirect:/admin-pizzeria/dashboard";
        }
        return "adminLogin"; // Retorna la vista de login
    }

    @PostMapping("/admin-pizzeria/login")
    public String validarLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        Empleado empleado = serviceEmpleado.validarEmpleado(email, password);

        if (empleado != null) {
            session.setAttribute("empleado", empleado);
            return "redirect:/admin-pizzeria/dashboard";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "adminLogin";
        }
    }

    @GetMapping("/admin-pizzeria/dashboard")
    public String mostrarDashBoard(HttpSession session, Model model) {
        Empleado empleado = (Empleado) session.getAttribute("empleado");

        if (empleado != null) {
            model.addAttribute("empleado", empleado);
            return "adminDashboard";
        } else {
            return "redirect:/admin-pizzeria/login";
        }
    }

    @GetMapping("/admin-pizzeria/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/admin-pizzeria/login";
    }
}
