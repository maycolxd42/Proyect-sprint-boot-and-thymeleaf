package com.ed.ecommerce.mvcDemo.Controller;

import com.ed.ecommerce.mvcDemo.Model.*;
import com.ed.ecommerce.mvcDemo.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/pizzeria")
public class ProductoController {

    @Autowired
    private ServiceProducto serviceProducto;

    @Autowired
    private ServicePedido servicePedido;

    @Autowired
    private ServiceDetallePedido serviceDetallePedido;

    @Autowired
    private ServiceTipoDocumento serviceTipoDocumento;

    @Autowired
    private ServiceTransaccion serviceTransaccion;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Producto> productos = serviceProducto.listarPorCategoria(1);
        List<Producto> productosLimitados = productos.subList(0, Math.min(4, productos.size()));

        model.addAttribute("listado", productosLimitados);
        return "index";
    }

    @GetMapping("/menu")
    public String mostrarMenu(HttpSession session, Model model) {
        List<Producto> pizzas = serviceProducto.listarPorCategoria(1);
        model.addAttribute("listadoPizzas", pizzas);

        List<Producto> bebidas = serviceProducto.listarPorCategoria(2);
        model.addAttribute("listadoBebidas", bebidas);

        return "menu";
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public ResponseEntity<?> agregarAlCarrito(@RequestParam int idProducto, HttpSession session) {

        if (session.getAttribute("cliente") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", "Debes iniciar sesión para agregar productos al carrito"
            ));
        }

        Producto producto = serviceProducto.obtenerProductoPorId(idProducto);
        if (producto != null) {
            List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
            if (productosCarrito == null) {
                productosCarrito = new ArrayList<>();
            }

            boolean productoExistente = false;
            for (Producto p : productosCarrito) {
                if (p.getIdProducto() == producto.getIdProducto()) {
                    p.setCantidad(p.getCantidad() + 1);
                    productoExistente = true;
                    break;
                }
            }

            if (!productoExistente) {
                producto.setCantidad(1);
                productosCarrito.add(producto);
            }

            session.setAttribute("productosCarrito", productosCarrito);

            // Calcular la cantidad total de productos en el carrito
            int totalCantidadProductos = productosCarrito.stream().mapToInt(Producto::getCantidad).sum();

            // Calcular el total
            double total = productosCarrito.stream()
                    .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                    .sum();

            return ResponseEntity.ok(Map.of(
                    "message", "Producto agregado al carrito",
                    "cartCount", totalCantidadProductos,
                    "total", total
            ));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", "Producto no encontrado"
        ));
    }


    @GetMapping("/cart")
    public String mostrarCarrito(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("cliente") == null) {
            redirectAttributes.addFlashAttribute("mensaje", "Debes iniciar sesión para ver tu carrito");
            return "redirect:/pizzeria/login";
        }

        // Si el cliente está autenticado, mostrar el carrito
        List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
        double total = 0.0;
        int totalCantidadProductos = 0;

        if (productosCarrito != null) {
            total = productosCarrito.stream()
                    .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                    .sum();
            totalCantidadProductos = productosCarrito.stream().mapToInt(Producto::getCantidad).sum();
        }

        List<TipoDocumento> tiposDocumento = serviceTipoDocumento.listarTiposDocumento();

        model.addAttribute("productosCarrito", productosCarrito);
        model.addAttribute("total", total);
        model.addAttribute("totalCantidadProductos", totalCantidadProductos);
        model.addAttribute("tiposDocumento", tiposDocumento);

        return "cart";
    }

    @PostMapping("/updateQuantity")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateQuantity(
            @RequestParam int idProducto,
            @RequestParam int cantidad,
            HttpSession session) {
        List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
        Map<String, Object> response = new HashMap<>();

        if (productosCarrito != null) {

            for (Producto p : productosCarrito) {
                if (p.getIdProducto() == idProducto) {
                    p.setCantidad(cantidad);
                    break;
                }
            }

            session.setAttribute("productosCarrito", productosCarrito);

            // Calcular el total a pagar y el total de productos
            double total = productosCarrito.stream()
                    .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                    .sum();
            int totalCantidadProductos = productosCarrito.stream().mapToInt(Producto::getCantidad).sum();

            response.put("total", total);
            response.put("cartCount", totalCantidadProductos);
            return ResponseEntity.ok(response);
        }

        response.put("total", 0.0);
        response.put("cartCount", 0);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    // Metodo para finalizar la compra
    @PostMapping("/checkout")
    public String finalizarCompra(@RequestParam int idCliente,
                                  @RequestParam int idTipoDocumento,
                                  HttpSession session, RedirectAttributes redirectAttributes) {

        List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");

        if (productosCarrito == null || productosCarrito.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "El carrito está vacío. Agrega productos para realizar la compra.");
            return "redirect:/pizzeria/cart"; // Redirige para mostrar el error
        }

        double total = productosCarrito.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();

        // Crear un objeto Pedido y setear los valores
        Pedido pedido = new Pedido();
        pedido.setIdCliente(idCliente);
        pedido.setTotal(total);
        pedido.setEstado("Pendiente");
        pedido.setFechaPedido(LocalDateTime.now());

        // Guardar el pedido en la base de datos y obtener su idPedido
        int idPedido = servicePedido.crearPedido(pedido);

        // Crear los detalles del pedido
        for (Producto producto : productosCarrito) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setIdPedido(idPedido);
            detallePedido.setIdProducto(producto.getIdProducto());
            detallePedido.setCantidad(producto.getCantidad());
            detallePedido.setPrecioUnitario(producto.getPrecio());
            detallePedido.setSubtotal(producto.getPrecio() * producto.getCantidad());
            serviceDetallePedido.crearDetallePedido(detallePedido);
        }

        // Obtener el TipoDocumento con el ID
        TipoDocumento tipoDocumento = serviceTipoDocumento.listarPorId(idTipoDocumento);

        if (tipoDocumento == null) {
            redirectAttributes.addFlashAttribute("error", "Tipo de documento no encontrado.");
            return "redirect:/pizzeria/cart";  // Redirige si el tipo de documento no es válido
        }

        // Crear la transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setIdpedido(idPedido);
        transaccion.setIdtipoDocumento(idTipoDocumento);
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setEstado("Pagado");

        // Guardar la transacción
        serviceTransaccion.crearTransaccion(transaccion);

        // Vaciar el carrito de la sesión
        session.removeAttribute("productosCarrito");

        redirectAttributes.addFlashAttribute("mensajeExito", "Compra realizada con éxito.");

        return "redirect:/pizzeria/cart";
    }


    @GetMapping("/getCartCount")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCartCount(HttpSession session) {
        List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
        int cartCount = 0;
        if (productosCarrito != null) {
            cartCount = productosCarrito.stream().mapToInt(Producto::getCantidad).sum();
        }

        return ResponseEntity.ok(Map.of("cartCount", cartCount));
    }

    @PostMapping("/removeFromCart")
    public String eliminarDelCarrito(@RequestParam int idProducto, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/login?mensaje=Debes iniciar sesión para eliminar productos del carrito";
        }

        List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
        if (productosCarrito != null) {
            productosCarrito.removeIf(producto -> producto.getIdProducto() == idProducto);
            session.setAttribute("productosCarrito", productosCarrito);
        }
        return "redirect:/pizzeria/cart";
    }
}
