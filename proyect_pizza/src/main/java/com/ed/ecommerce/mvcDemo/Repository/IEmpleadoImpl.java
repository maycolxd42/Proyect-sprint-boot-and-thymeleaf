package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Empleado;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IEmpleadoImpl implements IEmpleado {

    @Override
    public Empleado validarEmpleado(String email, String contrasena) {
        Empleado empleado = null;
        String query = "SELECT * FROM Empleado WHERE email = ? AND contrasena = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Establecer los parámetros de la consulta
            ps.setString(1, email);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Recuperar los datos del empleado y asignarlos al objeto
                    empleado = new Empleado(
                            rs.getInt("idEmpleado"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("contrasena")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public List<Empleado> mostrarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM Empleado";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("idEmpleado"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("contrasena")
                );
                empleados.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public boolean actualizarEmpleadoActual(Empleado empleado) {
        String query = "UPDATE Empleado SET nombre = ?, telefono = ?, email = ?, contrasena = ? WHERE idEmpleado = ?";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getTelefono());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getContrasena());
            ps.setInt(5, empleado.getIdEmpleado());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean anadirNuevoEmpleado(Empleado empleado) {
        boolean resultado = false;
        String query = "INSERT INTO Empleado (nombre, telefono, email, contrasena) VALUES (?, ?, ?, ?)";
        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Establecer los parámetros de la consulta, incluyendo el rol
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getTelefono());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getContrasena());

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                resultado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
