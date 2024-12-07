package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Model.Producto;
import com.ed.ecommerce.mvcDemo.Model.Inventario;
import com.ed.ecommerce.mvcDemo.Services.ServiceProducto;
import com.ed.ecommerce.mvcDemo.Services.ServiceInventario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminInventarioController {

    @Autowired
    private ServiceProducto serviceProducto;

    @Autowired
    private ServiceInventario serviceInventario;

    @GetMapping("/admin-pizzeria/inventarios")
    public String mostrarInventario(HttpSession session, Model model) {

        if (session.getAttribute("empleado") == null) {
            return "redirect:/admin-pizzeria/login";
        }

        List<Producto> productos = serviceProducto.listarProductos();

        List<Inventario> inventarios = serviceInventario.listarInventarios();

        model.addAttribute("productos", productos);
        model.addAttribute("inventarios", inventarios);

        return "adminInventario";
    }
}
