package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Cliente;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IClienteImpl implements ICliente {

    @Override
    public Cliente validarCliente(String email, String password) {
        String query = "SELECT * FROM Cliente WHERE email = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("contrasena");

                    // Verificar la contraseña
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    if (passwordEncoder.matches(password, hashedPassword)) {
                        return new Cliente(
                                rs.getInt("idCliente"),
                                rs.getString("nombreCompleto"),
                                rs.getString("email"),
                                rs.getString("contrasena"),
                                rs.getString("telefono"),
                                rs.getDate("fechaNacimiento")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registrar(Cliente cliente) {
        String query = "INSERT INTO Cliente (nombreCompleto, email, contrasena, telefono, fechaNacimiento) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Encriptar contraseña
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(cliente.getContrasena());

            ps.setString(1, cliente.getNombreCompleto());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, hashedPassword); // Guardar contraseña encriptada
            ps.setString(4, cliente.getTelefono());
            ps.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Cliente> obtenerClientes() {
        String query = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombreCompleto"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getDate("fechaNacimiento")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
