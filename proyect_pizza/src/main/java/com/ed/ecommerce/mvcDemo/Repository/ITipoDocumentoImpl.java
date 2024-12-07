package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.TipoDocumento;
import com.ed.ecommerce.mvcDemo.Pattern.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ITipoDocumentoImpl implements ITipoDocumento {

    // Listar todos los tipos de documento
    @Override
    public List<TipoDocumento> listarTiposDocumento() {
        List<TipoDocumento> tiposDocumento = new ArrayList<>();
        String query = "SELECT * FROM TipoDocumento";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setIdTipoDocumento(rs.getInt("idTipoDocumento"));
                tipoDocumento.setNombre(rs.getString("nombre"));
                tiposDocumento.add(tipoDocumento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposDocumento;
    }

    // Listar tipo de documento por ID
    @Override
    public TipoDocumento listarPorId(int id) {
        TipoDocumento tipoDocumento = null;
        String query = "SELECT * FROM TipoDocumento WHERE idTipoDocumento = ?";

        try (Connection con = conexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipoDocumento = new TipoDocumento();
                    tipoDocumento.setIdTipoDocumento(rs.getInt("idTipoDocumento"));
                    tipoDocumento.setNombre(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoDocumento;
    }
}