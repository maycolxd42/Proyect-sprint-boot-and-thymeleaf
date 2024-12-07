package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Transaccion;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ITransaccionImpl implements ITransaccion {

    // Listar todas las transacciones
    @Override
    public List<Transaccion> listarTransacciones() {
        List<Transaccion> transacciones = new ArrayList<>();
        String query = "SELECT * FROM Transaccion";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setIdTransaccion(rs.getInt("idTransaccion"));
                transaccion.setIdpedido(rs.getInt("idPedido"));
                transaccion.setIdtipoDocumento(rs.getInt("idTipoDocumento"));
                transaccion.setEstado(rs.getString("estado"));

                // Obtener el Timestamp y convertirlo a LocalDateTime
                Timestamp timestamp = rs.getTimestamp("fechaTransaccion");
                if (timestamp != null) {
                    transaccion.setFechaTransaccion(timestamp.toLocalDateTime());
                }

                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    // Listar transacción por ID
    @Override
    public Transaccion listarPorId(int id) {
        Transaccion transaccion = null;
        String query = "SELECT * FROM Transaccion WHERE idTransaccion = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    transaccion = new Transaccion();
                    transaccion.setIdTransaccion(rs.getInt("idTransaccion"));
                    transaccion.setIdpedido(rs.getInt("idPedido"));
                    transaccion.setIdtipoDocumento(rs.getInt("idTipoDocumento"));
                    transaccion.setEstado(rs.getString("estado"));

                    // Obtener el Timestamp y convertirlo a LocalDateTime
                    Timestamp timestamp = rs.getTimestamp("fechaTransaccion");
                    if (timestamp != null) {
                        transaccion.setFechaTransaccion(timestamp.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaccion;
    }

    // Obtener transacción por ID de Pedido
    @Override
    public Transaccion obtenerTransaccionPorIdPedido(int idPedido) {
        Transaccion transaccion = null;
        String query = "SELECT * FROM Transaccion WHERE idPedido = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    transaccion = new Transaccion();
                    transaccion.setIdTransaccion(rs.getInt("idTransaccion"));
                    transaccion.setIdpedido(rs.getInt("idPedido"));
                    transaccion.setIdtipoDocumento(rs.getInt("idTipoDocumento"));
                    transaccion.setEstado(rs.getString("estado"));

                    // Obtener el Timestamp y convertirlo a LocalDateTime
                    Timestamp timestamp = rs.getTimestamp("fechaTransaccion");
                    if (timestamp != null) {
                        transaccion.setFechaTransaccion(timestamp.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaccion;
    }

    // Crear una nueva transacción
    @Override
    public int crear(Transaccion transaccion) {
        String query = "INSERT INTO Transaccion (idPedido, idTipoDocumento, estado, fechaTransaccion) VALUES (?, ?, ?, ?)";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, transaccion.getIdpedido());
            ps.setInt(2, transaccion.getIdTipoDocumento());
            ps.setString(3, transaccion.getEstado());

            // Convertir LocalDateTime a Timestamp antes de insertar
            if (transaccion.getFechaTransaccion() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(transaccion.getFechaTransaccion()));
            } else {
                ps.setTimestamp(4, null);  // Manejo de valor nulo si la fecha es nula
            }

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Modificar una transacción existente
    @Override
    public int modificar(Transaccion transaccion) {
        String query = "UPDATE Transaccion SET idPedido = ?, idTipoDocumento = ?, estado = ?, fechaTransaccion = ? WHERE idTransaccion = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, transaccion.getIdpedido());
            ps.setInt(2, transaccion.getIdTipoDocumento());
            ps.setString(3, transaccion.getEstado());

            // Convertir LocalDateTime a Timestamp antes de actualizar
            if (transaccion.getFechaTransaccion() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(transaccion.getFechaTransaccion()));
            } else {
                ps.setTimestamp(4, null);  // Manejo de valor nulo si la fecha es nula
            }

            ps.setInt(5, transaccion.getIdTransaccion());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Eliminar una transacción por ID
    @Override
    public int eliminar(int id) {
        String query = "DELETE FROM Transaccion WHERE idTransaccion = ?";
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
