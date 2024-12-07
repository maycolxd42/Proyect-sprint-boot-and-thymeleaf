package com.ed.ecommerce.mvcDemo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizzeria")
public class otherviewsController {

    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "nosotros"; // Devolverá la vista nosotros.html
    }

    @GetMapping("/soporte")
    public String mostrarSoporte() {
        return "soporte"; // Devolverá la vista nosotros.html
    }
}