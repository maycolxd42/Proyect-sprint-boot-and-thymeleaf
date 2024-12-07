package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.Cliente;
import com.ed.ecommerce.mvcDemo.Model.DetallePedido;
import com.ed.ecommerce.mvcDemo.Model.Pedido;
import com.ed.ecommerce.mvcDemo.Services.ServiceCliente;
import com.ed.ecommerce.mvcDemo.Services.ServiceDetallePedido;
import com.ed.ecommerce.mvcDemo.Services.ServicePedido;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Controller
@RequestMapping("/admin-pizzeria")
public class AdminPedidoController {

    @Autowired
    private ServicePedido servicePedido;

    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private ServiceDetallePedido serviceDetallePedido;

    @GetMapping("/control-pedidos")
    public String mostrarPedidos(HttpSession session, Model model) {

        if (session.getAttribute("empleado") == null) {
            return "redirect:/admin-pizzeria/login";
        }

        List<Pedido> pedidos = servicePedido.listarPedidos();
        List<Cliente> clientes = serviceCliente.obtenerClientes();

        // Formatear la fecha de cada pedido
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Pedido pedido : pedidos) {
            if (pedido.getFechaPedido() != null) {
                String fechaFormateada = pedido.getFechaPedido().format(formatter);
                pedido.setFechaPedidoFormateada(fechaFormateada); // Suponiendo que tienes un setter para la fecha formateada
            }
        }

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("clientes", clientes);
        return "adminPedido";
    }

    @PostMapping("/modificar-pedido")
    public String modificarEstadoPedido(@RequestParam("idPedido") int idPedido,
                                        @RequestParam("nuevoEstado") String nuevoEstado) {
        Pedido pedido = servicePedido.listarPorId(idPedido);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            servicePedido.modificarPedido(pedido);
        }
        return "redirect:/admin-pizzeria/control-pedidos";
    }

    @PostMapping("/eliminar-pedido")
    public String eliminarPedido(@RequestParam("idPedido") int idPedido) {
        servicePedido.eliminarPedido(idPedido);
        return "redirect:/admin-pizzeria/control-pedidos";
    }


}