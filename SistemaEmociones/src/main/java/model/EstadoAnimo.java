/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juanc
 */

import java.time.LocalDate;

public class EstadoAnimo {

    private int id;           
    private int usuarioId;    
    private LocalDate fecha;
    private String emocion;
    private int intensidad;
    private String nota;

    public EstadoAnimo() {
        this.fecha = LocalDate.now();
    }

    public EstadoAnimo(int usuarioId, String emocion, int intensidad, String nota) {
        this.usuarioId = usuarioId;
        this.fecha = LocalDate.now();
        this.emocion = emocion;
        this.intensidad = intensidad;
        this.nota = nota;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEmocion() { return emocion; }
    public void setEmocion(String emocion) { this.emocion = emocion; }

    public int getIntensidad() { return intensidad; }
    public void setIntensidad(int intensidad) { this.intensidad = intensidad; }

    public String getNota() { return nota; }
    public void setNota(String nota) { this.nota = nota; }

    @Override
    public String toString() {
        return fecha + " - " + emocion + " (" + intensidad + "/10)";
    }
}
