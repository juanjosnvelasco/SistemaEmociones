/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author juanc
 */

import model.EstadoAnimo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoAnimoDAO {

    
    public boolean guardar(EstadoAnimo e) {
        String sql = "INSERT INTO estados_animo (usuario_id, emocion, intensidad, nota, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getUsuarioId());
            ps.setString(2, e.getEmocion());
            ps.setInt(3, e.getIntensidad());
            ps.setString(4, e.getNota());
            ps.setDate(5, Date.valueOf(e.getFecha())); 

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al guardar: " + ex.getMessage());
            return false;
        }
    }

    
    public List<EstadoAnimo> obtenerHistorial(int usuarioId) {
        List<EstadoAnimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM estados_animo WHERE usuario_id = ? ORDER BY fecha DESC";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EstadoAnimo ea = new EstadoAnimo();
                ea.setId(rs.getInt("id"));
                ea.setUsuarioId(rs.getInt("usuario_id"));
                ea.setEmocion(rs.getString("emocion"));
                ea.setIntensidad(rs.getInt("intensidad"));
                ea.setNota(rs.getString("nota"));
                ea.setFecha(rs.getDate("fecha").toLocalDate());
                lista.add(ea);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener historial: " + ex.getMessage());
        }
        return lista;
    }
}