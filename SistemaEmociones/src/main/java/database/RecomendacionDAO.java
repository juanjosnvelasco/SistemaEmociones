/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author juanc
 */
import model.Recomendacion;
import java.sql.*;
import java.time.LocalDate;

public class RecomendacionDAO {

    public Recomendacion obtenerRecomendacionDelDia(int usuarioId, String emocion) {

        
        String emocionLimpia = emocion.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "").trim();
        String hoy = LocalDate.now().toString();

        String sql = "SELECT r.* FROM recomendaciones r " +
                     "WHERE r.emocion_relacionada = ? " +
                     "AND r.id NOT IN (" +
                     "  SELECT recomendacion_id FROM historial_recomendaciones " +
                     "  WHERE usuario_id = ? AND fecha_mostrada = ?" +
                     ") ORDER BY RAND() LIMIT 1";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emocionLimpia);
            ps.setInt(2, usuarioId);
            ps.setString(3, hoy);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Recomendacion r = new Recomendacion();
                r.setId(rs.getInt("id"));
                r.setEmocionRelacionada(rs.getString("emocion_relacionada"));
                r.setActividad(rs.getString("actividad"));
                r.setExplicacionPsicologica(rs.getString("explicacion"));

                registrarMostrada(usuarioId, r.getId());
                return r;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener recomendacion: " + e.getMessage());
        }
        return null;
    }

    private void registrarMostrada(int usuarioId, int recomendacionId) {
        String sql = "INSERT INTO historial_recomendaciones " +
                     "(usuario_id, recomendacion_id, fecha_mostrada) VALUES (?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, recomendacionId);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar historial: " + e.getMessage());
        }
    }
}