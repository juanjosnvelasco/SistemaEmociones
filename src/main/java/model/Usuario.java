/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juanc
 */
public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String contrasena;

    
    public Usuario() {}

    
    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    
    public Usuario(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre=" + nombre + ", email=" + email + "}";
    }
}