package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Pedido;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IPedidoImpl implements IPedido {

    // Listar todos los pedidos
    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT * FROM Pedido";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                // Obtener la fecha con hora como LocalDateTime
                Timestamp timestamp = rs.getTimestamp("fechaPedido");
                if (timestamp != null) {
                    pedido.setFechaPedido(timestamp.toLocalDateTime()); // Convertir Timestamp a LocalDateTime
                }

                pedido.setTotal(rs.getDouble("total")); // Cambiado de rs.getBigDecimal a rs.getDouble
                pedido.setEstado(rs.getString("estado"));

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    // Listar pedido por ID
    @Override
    public Pedido listarPorId(int id) {
        Pedido pedido = null;
        String query = "SELECT * FROM Pedido WHERE idPedido = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido();
                    pedido.setIdPedido(rs.getInt("idPedido"));
                    pedido.setIdCliente(rs.getInt("idCliente"));
                    // Obtener la fecha con hora como LocalDateTime
                    Timestamp timestamp = rs.getTimestamp("fechaPedido");
                    if (timestamp != null) {
                        pedido.setFechaPedido(timestamp.toLocalDateTime());  // Convertir Timestamp a LocalDateTime
                    }
                    pedido.setTotal(rs.getDouble("total")); // Cambiado de rs.getBigDecimal a rs.getDouble
                    pedido.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    // Crear un nuevo pedido
    @Override
    public int crear(Pedido pedido) {
        String query = "INSERT INTO Pedido (idCliente, fechaPedido, total, estado) VALUES (?, ?, ?, ?)";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, pedido.getIdCliente());
            // Convertir LocalDateTime a Timestamp antes de insertar
            if (pedido.getFechaPedido() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(pedido.getFechaPedido()));
            } else {
                ps.setTimestamp(2, null);  // Manejo de valor nulo si la fecha es nula
            }
            ps.setDouble(3, pedido.getTotal()); // Cambiado de setBigDecimal a setDouble
            ps.setString(4, pedido.getEstado());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Modificar un pedido existente
    @Override
    public int modificar(Pedido pedido) {
        String query = "UPDATE Pedido SET idCliente = ?, fechaPedido = ?, total = ?, estado = ? WHERE idPedido = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, pedido.getIdCliente());
            // Convertir LocalDateTime a Timestamp antes de actualizar
            if (pedido.getFechaPedido() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(pedido.getFechaPedido()));
            } else {
                ps.setTimestamp(2, null);  // Manejo de valor nulo si la fecha es nula
            }
            ps.setDouble(3, pedido.getTotal()); // Cambiado de setBigDecimal a setDouble
            ps.setString(4, pedido.getEstado());
            ps.setInt(5, pedido.getIdPedido());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Eliminar un pedido por ID
    @Override
    public int eliminar(int id) {
        String query = "DELETE FROM Pedido WHERE idPedido = ?";
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
