/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author juanc
 */
import database.UsuarioDAO;
import model.Usuario;

public class UsuarioController {

    private UsuarioDAO dao = new UsuarioDAO();

    
    public boolean registrar(Usuario u) {
        return dao.registrar(u);
    }

    
    public boolean registrar(String nombre, String email, String contrasena) {
        Usuario u = new Usuario(nombre, email, contrasena);
        return dao.registrar(u);
    }

    
    public Usuario login(String email, String contrasena) {
        return dao.login(email, contrasena);
    }

    
    public Usuario login(Usuario u) {
        return dao.login(u.getEmail(), u.getContrasena());
    }
}