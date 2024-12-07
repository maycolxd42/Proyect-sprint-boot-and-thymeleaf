package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Inventario;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IInventarioImpl implements IInventario {

    // Listar todos los inventarios
    @Override
    public List<Inventario> Listar() {
        List<Inventario> inventarios = new ArrayList<>();
        String query = "SELECT * FROM Inventario";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdInventario(rs.getInt("idInventario"));
                inventario.setIdProducto(rs.getInt("idProducto"));
                inventario.setCantidadDisponible(rs.getInt("cantidadDisponible"));

                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    // Crear un nuevo inventario
    @Override
    public int crear(Inventario inventario) {
        String query = "INSERT INTO Inventario (idProducto, cantidadDisponible) VALUES (?, ?)";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, inventario.getIdProducto());
            ps.setInt(2, inventario.getCantidadDisponible());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Modificar un inventario existente
    @Override
    public int modificar(Inventario inventario) {
        String query = "UPDATE Inventario SET idProducto = ?, cantidadDisponible = ? WHERE idInventario = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, inventario.getIdProducto());
            ps.setInt(2, inventario.getCantidadDisponible());
            ps.setInt(3, inventario.getIdInventario());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Eliminar un inventario por ID
    @Override
    public int eliminar(int id) {
        String query = "DELETE FROM Inventario WHERE idInventario = ?";
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
