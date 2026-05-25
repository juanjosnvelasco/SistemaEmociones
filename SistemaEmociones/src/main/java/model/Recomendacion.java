/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juanc
 */
public class Recomendacion {

    private int id;
    private String emocionRelacionada;
    private String actividad;
    private String explicacionPsicologica;

    
    public Recomendacion() {}

    
    public Recomendacion(String actividad) {
        this.actividad = actividad;
    }

    
    public Recomendacion(String emocionRelacionada, String actividad) {
        this.emocionRelacionada = emocionRelacionada;
        this.actividad = actividad;
    }

    
    public Recomendacion(String emocionRelacionada, String actividad, String explicacionPsicologica) {
        this.emocionRelacionada = emocionRelacionada;
        this.actividad = actividad;
        this.explicacionPsicologica = explicacionPsicologica;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmocionRelacionada() { return emocionRelacionada; }
    public void setEmocionRelacionada(String emocionRelacionada) { this.emocionRelacionada = emocionRelacionada; }

    public String getActividad() { return actividad; }
    public void setActividad(String actividad) { this.actividad = actividad; }

    public String getExplicacionPsicologica() { return explicacionPsicologica; }
    public void setExplicacionPsicologica(String explicacionPsicologica) { this.explicacionPsicologica = explicacionPsicologica; }

    @Override
    public String toString() {
        return emocionRelacionada + " - " + actividad;
    }
}