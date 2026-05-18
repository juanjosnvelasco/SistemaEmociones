/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author juanc
 */


import database.EstadoAnimoDAO;
import model.EstadoAnimo;
import java.util.List;

public class EmocionController {

    private EstadoAnimoDAO dao = new EstadoAnimoDAO();

    public boolean registrarEmocion(int usuarioId, String emocion, int intensidad, String nota) {
        EstadoAnimo ea = new EstadoAnimo(usuarioId, emocion, intensidad, nota);
        return dao.guardar(ea);
    }

    public List<EstadoAnimo> obtenerHistorial(int usuarioId) {
        return dao.obtenerHistorial(usuarioId);
    }
}