package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.Producto;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IProductoImpl implements IProducto {

    // Listar todos los productos
    @Override
    public List<Producto> ListarProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setImagen(rs.getString("imagen"));
                producto.setIdCategoriaProducto(rs.getInt("idCategoriaProducto"));

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Listar producto por ID
    @Override
    public Producto ListarPorId(int id) {
        Producto producto = null;
        String query = "SELECT * FROM Producto WHERE idProducto = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setImagen(rs.getString("imagen"));
                    producto.setIdCategoriaProducto(rs.getInt("idCategoriaProducto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Listar productos por categoría
    @Override
    public List<Producto> ListarPorCategoria(int idCategoriaProducto) {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto WHERE idCategoriaProducto = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idCategoriaProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setImagen(rs.getString("imagen"));
                    producto.setIdCategoriaProducto(rs.getInt("idCategoriaProducto"));

                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        String query = "SELECT * FROM Producto WHERE idProducto = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setImagen(rs.getString("imagen"));
                    producto.setIdCategoriaProducto(rs.getInt("idCategoriaProducto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }


    // Crear un nuevo producto
    @Override
    public int crear(Producto producto) {
        String query = "INSERT INTO Producto (nombre, descripcion, precio, imagen, idCategoriaProducto) VALUES (?, ?, ?, ?, ?)";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getImagen());
            ps.setInt(5, producto.getIdCategoriaProducto());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Modificar un producto existente
    @Override
    public int modificar(Producto producto) {
        String query = "UPDATE Producto SET nombre = ?, descripcion = ?, precio = ?, imagen = ?, idCategoriaProducto = ? WHERE idProducto = ?";
        int resultado = 0;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getImagen());
            ps.setInt(5, producto.getIdCategoriaProducto());
            ps.setInt(6, producto.getIdProducto());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Eliminar un producto por ID
    @Override
    public int eliminar(int id) {
        String query = "DELETE FROM Producto WHERE idProducto = ?";
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
