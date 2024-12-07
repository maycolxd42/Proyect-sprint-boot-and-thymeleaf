package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Model.Soporte;
import com.ed.ecommerce.mvcDemo.Services.ServiceSoporte;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/admin-pizzeria")
public class AdminSoporteController {

    @Autowired
    private ServiceSoporte serviceSoporte;

    // Endpoint para recibir el mensaje del chatbot y guardarlo
    @PostMapping("/soporte")
    public String recibirMensaje(@RequestBody Soporte soporte, RedirectAttributes redirectAttributes) {
        try {
            int resultado = serviceSoporte.guardarSoporte(soporte);

            if (resultado > 0) {
                redirectAttributes.addFlashAttribute("message", "Mensaje recibido correctamente.");
            } else {
                redirectAttributes.addFlashAttribute("message", "Hubo un error al recibir el mensaje.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error inesperado al procesar el mensaje.");
        }

        return "redirect:/admin-pizzeria/soporte";
    }

    // Endpoint para mostrar todos los mensajes de soporte
    @GetMapping("/soporte")
    public String verSoporte(HttpSession session, Model model) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/admin-pizzeria/login";
        }
        // Obtener todos los mensajes de soporte
        List<Soporte> mensajes = serviceSoporte.obtenerTodos();
        model.addAttribute("soportes", mensajes);

        return "adminSoporte"; // Vista que muestra los mensajes de soporte
    }

    // Endpoint para eliminar un mensaje de soporte
    @PostMapping("/soporte/borrar/{idSoporte}")
    public String borrarMensaje(@PathVariable int idSoporte, RedirectAttributes redirectAttributes) {
        try {
            // Eliminar el mensaje de soporte
            serviceSoporte.eliminarSoporte(idSoporte);
            redirectAttributes.addFlashAttribute("message", "Mensaje eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Hubo un error al eliminar el mensaje.");
        }

        return "redirect:/admin-pizzeria/soporte";
    }


    // Endpoint para mostrar el mensaje de soporte a responder
    @GetMapping("/soporte/responder/{id}")
    public String mostrarRespuesta(@PathVariable int id, Model model) {
        Soporte soporte = serviceSoporte.obtenerPorId(id);
        if (soporte == null) {
            model.addAttribute("errorMessage", "Mensaje de soporte no encontrado.");
            return "errorPage";
        }
        model.addAttribute("soporte", soporte);
        return "adminResponseSoporte";
    }

    // Endpoint para enviar la respuesta por correo electrónico
    @PostMapping("/soporte/responder/{id}")
    public String enviarRespuesta(@PathVariable int id, @RequestParam String email, @RequestParam String respuesta, RedirectAttributes redirectAttributes) {
        try {
            String subject = "Respuesta a su consulta";
            serviceSoporte.enviarRespuesta(email, subject, respuesta);
            redirectAttributes.addFlashAttribute("message", "Respuesta enviada correctamente.");
            redirectAttributes.addFlashAttribute("status", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al enviar la respuesta.");
            redirectAttributes.addFlashAttribute("status", "error");
            e.printStackTrace();
        }
        return "redirect:/admin-pizzeria/soporte/responder/" + id;
    }
}
