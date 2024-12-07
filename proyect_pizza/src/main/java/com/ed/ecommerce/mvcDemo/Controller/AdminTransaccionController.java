package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Model.Transaccion;
import com.ed.ecommerce.mvcDemo.Services.ServiceTransaccion;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin-pizzeria")
public class AdminTransaccionController {

    @Autowired
    private ServiceTransaccion serviceTransaccion;

    // Metodo para mostrar las transacciones
    @GetMapping("/control-transacciones")
    public String mostrarTransacciones(HttpSession session, Model model) {

        if (session.getAttribute("empleado") == null) {
            return "redirect:/admin-pizzeria/login";
        }

        List<Transaccion> transacciones = serviceTransaccion.listarTransacciones();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Transaccion transaccion : transacciones) {
            String fechaFormateada = transaccion.getFechaTransaccion().format(formatter);
            transaccion.setFechaTransaccionFormateada(fechaFormateada);
        }

        model.addAttribute("transacciones", transacciones);

        return "adminTransacciones";
    }
}
