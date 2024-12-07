package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.DetallePedido;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IDetallePedidoImpl implements IDetallePedido {

    // Listar detalles de un pedido
    @Override
    public List<DetallePedido> listarDetallesPorPedido(int idPedido) {
        List<DetallePedido> detalles = new ArrayList<>();
        String query = "SELECT * FROM DetallePedido WHERE idPedido = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetallePedido detalle = new DetallePedido();
                    detalle.setIdDetallePedido(rs.getInt("idDetallePedido"));
                    detalle.setIdPedido(rs.getInt("idPedido"));
                    detalle.setIdProducto(rs.getInt("idProducto"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    detalle.setSubtotal(rs.getDouble("subtotal"));

                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    // Listar detalle por ID
    @Override
    public DetallePedido listarPorId(int id) {
        DetallePedido detalle = null;
        String query = "SELECT * FROM DetallePedido WHERE idDetallePedido = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detalle = new DetallePedido();
                    detalle.setIdDetallePedido(rs.getInt("idDetallePedido"));
                    detalle.setIdPedido(rs.getInt("idPedido"));
                    detalle.setIdProducto(rs.getInt("idProducto"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    detalle.setSubtotal(rs.getDouble("subtotal"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalle;
    }

    // Crear un nuevo detalle de pedido
    @Override
    public int crear(DetallePedido detallePedido) {
        String query = "INSERT INTO DetallePedido (idPedido, idProducto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, detallePedido.getIdPedido());
            ps.setInt(2, detallePedido.getIdProducto());
            ps.setInt(3, detallePedido.getCantidad());
            ps.setDouble(4, detallePedido.getPrecioUnitario());
            ps.setDouble(5, detallePedido.getSubtotal());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Modificar un detalle de pedido
    @Override
    public int modificar(DetallePedido detallePedido) {
        String query = "UPDATE DetallePedido SET cantidad = ?, precioUnitario = ?, subtotal = ? WHERE idDetallePedido = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, detallePedido.getCantidad());
            ps.setDouble(2, detallePedido.getPrecioUnitario());
            ps.setDouble(3, detallePedido.getSubtotal());
            ps.setInt(4, detallePedido.getIdDetallePedido());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Eliminar un detalle de pedido
    @Override
    public int eliminar(int id) {
        String query = "DELETE FROM DetallePedido WHERE idDetallePedido = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
