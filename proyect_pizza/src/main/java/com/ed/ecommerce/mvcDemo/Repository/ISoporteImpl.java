package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Soporte;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ISoporteImpl implements ISoporte {

    @Autowired
    private JavaMailSender mailSender;  // Inyección de JavaMailSender

    @Override
    public int guardar(Soporte soporte) {
        String query = "INSERT INTO Soporte (nombre, email, mensaje, fecha) VALUES (?, ?, ?, ?)";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, soporte.getNombre());
            ps.setString(2, soporte.getEmail());
            ps.setString(3, soporte.getMensaje());
            ps.setTimestamp(4, new Timestamp(soporte.getFecha().getTime()));

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List<Soporte> obtenerTodos() {
        String query = "SELECT * FROM Soporte";
        List<Soporte> soportes = new ArrayList<>();

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Soporte soporte = new Soporte(rs.getString("nombre"), rs.getString("email"), rs.getString("mensaje"));
                soporte.setIdSoporte(rs.getInt("idSoporte"));
                soporte.setFecha(rs.getTimestamp("fecha"));
                soportes.add(soporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soportes;
    }

    @Override
    public Soporte obtenerPorId(int idSoporte) {
        String query = "SELECT * FROM Soporte WHERE idSoporte = ?";
        Soporte soporte = null;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idSoporte);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    soporte = new Soporte(rs.getString("nombre"), rs.getString("email"), rs.getString("mensaje"));
                    soporte.setIdSoporte(rs.getInt("idSoporte"));
                    soporte.setFecha(rs.getTimestamp("fecha"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soporte;
    }

    @Override
    public int eliminarPorId(int idSoporte) {
        String query = "DELETE FROM Soporte WHERE idSoporte = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idSoporte);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public void enviarRespuesta(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
