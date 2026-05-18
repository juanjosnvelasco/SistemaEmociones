/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juanc
 */

public class Frase {
    
    private String texto;
    private String autor;
    
    public Frase() {}
    
    public Frase(String texto, String autor) {
        this.texto = texto;
        this.autor = autor;
    }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "\"" + texto + "\"\n- " + (autor != null ? autor : "Anónimo");
    }
}
//f
