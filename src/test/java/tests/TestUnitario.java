/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tests;
/**
 *
 * @author juanc
 */

import model.Usuario;
import model.EstadoAnimo;
import model.Recomendacion;
import model.Frase;
import controller.UsuarioController;
import controller.EmocionController;
import java.time.LocalDate;

public class TestUnitario {

    static int exitos = 0;
    static int fallos = 0;

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      PRUEBAS UNITARIAS - EmoAdapt    ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        testUsuarioConstructorVacio();
        testUsuarioConstructorNombre();
        testUsuarioConstructorNombreEmail();
        testUsuarioConstructorCompleto();
        testUsuarioGettersSetters();
        testUsuarioToString();

        testEstadoAnimoConstructorVacio();
        testEstadoAnimoConstructorEmocion();
        testEstadoAnimoConstructorEmocionIntensidad();
        testEstadoAnimoConstructorCompleto();
        testEstadoAnimoFechaAutomatica();
        testEstadoAnimoToString();

        testRecomendacionConstructorVacio();
        testRecomendacionConstructorActividad();
        testRecomendacionConstructorEmocionActividad();
        testRecomendacionConstructorCompleto();

        testFraseConstructorVacio();
        testFraseConstructorTexto();
        testFraseConstructorCompleto();
        testFraseToString();

        testSobrecargaRegistrar();
        testSobrecargaLogin();
        testSobrecargaRegistrarEmocion();
        testSobrecargaObtenerHistorial();

        resumen();
    }

    

    static void testUsuarioConstructorVacio() {
        try {
            Usuario u = new Usuario();
            assert u.getNombre() == null;
            pasar("Usuario() - constructor vacio OK");
        } catch (Exception e) { fallar("Usuario() - " + e.getMessage()); }
    }

    static void testUsuarioConstructorNombre() {
        try {
            Usuario u = new Usuario("Juan");
            assert u.getNombre().equals("Juan");
            pasar("Usuario(nombre) - sobrecarga OK");
        } catch (Exception e) { fallar("Usuario(nombre) - " + e.getMessage()); }
    }

    static void testUsuarioConstructorNombreEmail() {
        try {
            Usuario u = new Usuario("Juan", "juan@gmail.com");
            assert u.getNombre().equals("Juan");
            assert u.getEmail().equals("juan@gmail.com");
            pasar("Usuario(nombre, email) - sobrecarga OK");
        } catch (Exception e) { fallar("Usuario(nombre, email) - " + e.getMessage()); }
    }

    static void testUsuarioConstructorCompleto() {
        try {
            Usuario u = new Usuario("Juan", "juan@gmail.com", "1234");
            assert u.getNombre().equals("Juan");
            assert u.getEmail().equals("juan@gmail.com");
            assert u.getContrasena().equals("1234");
            pasar("Usuario(nombre, email, contrasena) - sobrecarga OK");
        } catch (Exception e) { fallar("Usuario completo - " + e.getMessage()); }
    }

    static void testUsuarioGettersSetters() {
        try {
            Usuario u = new Usuario();
            u.setId(1);
            u.setNombre("Maria");
            u.setEmail("maria@gmail.com");
            u.setContrasena("abcd");
            assert u.getId() == 1;
            assert u.getNombre().equals("Maria");
            assert u.getEmail().equals("maria@gmail.com");
            assert u.getContrasena().equals("abcd");
            pasar("Usuario getters/setters - OK");
        } catch (Exception e) { fallar("Usuario getters/setters - " + e.getMessage()); }
    }

    static void testUsuarioToString() {
        try {
            Usuario u = new Usuario("Pedro", "pedro@gmail.com", "pass");
            u.setId(5);
            String resultado = u.toString();
            assert resultado.contains("Pedro");
            assert resultado.contains("pedro@gmail.com");
            pasar("Usuario toString() - OK");
        } catch (Exception e) { fallar("Usuario toString() - " + e.getMessage()); }
    }

    
    static void testEstadoAnimoConstructorVacio() {
        try {
            EstadoAnimo ea = new EstadoAnimo();
            assert ea.getFecha() != null;
            pasar("EstadoAnimo() - constructor vacio OK");
        } catch (Exception e) { fallar("EstadoAnimo() - " + e.getMessage()); }
    }

    static void testEstadoAnimoConstructorEmocion() {
        try {
            EstadoAnimo ea = new EstadoAnimo("Alegre");
            assert ea.getEmocion().equals("Alegre");
            pasar("EstadoAnimo(emocion) - sobrecarga OK");
        } catch (Exception e) { fallar("EstadoAnimo(emocion) - " + e.getMessage()); }
    }

    static void testEstadoAnimoConstructorEmocionIntensidad() {
        try {
            EstadoAnimo ea = new EstadoAnimo("Triste", 7);
            assert ea.getEmocion().equals("Triste");
            assert ea.getIntensidad() == 7;
            pasar("EstadoAnimo(emocion, intensidad) - sobrecarga OK");
        } catch (Exception e) { fallar("EstadoAnimo(emocion, intensidad) - " + e.getMessage()); }
    }

    static void testEstadoAnimoConstructorCompleto() {
        try {
            EstadoAnimo ea = new EstadoAnimo(2, "Ansioso", 9, "Examen manana");
            assert ea.getUsuarioId() == 2;
            assert ea.getEmocion().equals("Ansioso");
            assert ea.getIntensidad() == 9;
            assert ea.getNota().equals("Examen manana");
            pasar("EstadoAnimo completo - sobrecarga OK");
        } catch (Exception e) { fallar("EstadoAnimo completo - " + e.getMessage()); }
    }

    static void testEstadoAnimoFechaAutomatica() {
        try {
            EstadoAnimo ea = new EstadoAnimo("Motivado", 8);
            assert ea.getFecha().equals(LocalDate.now());
            pasar("EstadoAnimo fecha automatica - OK");
        } catch (Exception e) { fallar("EstadoAnimo fecha - " + e.getMessage()); }
    }

    static void testEstadoAnimoToString() {
        try {
            EstadoAnimo ea = new EstadoAnimo("Alegre", 10);
            String resultado = ea.toString();
            assert resultado.contains("Alegre");
            assert resultado.contains("10");
            pasar("EstadoAnimo toString() - OK");
        } catch (Exception e) { fallar("EstadoAnimo toString() - " + e.getMessage()); }
    }

    
    static void testRecomendacionConstructorVacio() {
        try {
            Recomendacion r = new Recomendacion();
            assert r.getActividad() == null;
            pasar("Recomendacion() - constructor vacio OK");
        } catch (Exception e) { fallar("Recomendacion() - " + e.getMessage()); }
    }

    static void testRecomendacionConstructorActividad() {
        try {
            Recomendacion r = new Recomendacion("Sal a caminar");
            assert r.getActividad().equals("Sal a caminar");
            pasar("Recomendacion(actividad) - sobrecarga OK");
        } catch (Exception e) { fallar("Recomendacion(actividad) - " + e.getMessage()); }
    }

    static void testRecomendacionConstructorEmocionActividad() {
        try {
            Recomendacion r = new Recomendacion("Triste", "Escucha musica");
            assert r.getEmocionRelacionada().equals("Triste");
            assert r.getActividad().equals("Escucha musica");
            pasar("Recomendacion(emocion, actividad) - sobrecarga OK");
        } catch (Exception e) { fallar("Recomendacion(emocion, actividad) - " + e.getMessage()); }
    }

    static void testRecomendacionConstructorCompleto() {
        try {
            Recomendacion r = new Recomendacion("Ansioso", "Respira", "Activa el nervio vago");
            assert r.getEmocionRelacionada().equals("Ansioso");
            assert r.getActividad().equals("Respira");
            assert r.getExplicacionPsicologica().equals("Activa el nervio vago");
            pasar("Recomendacion completa - sobrecarga OK");
        } catch (Exception e) { fallar("Recomendacion completa - " + e.getMessage()); }
    }

    
    static void testFraseConstructorVacio() {
        try {
            Frase f = new Frase();
            assert f.getTexto() == null;
            pasar("Frase() - constructor vacio OK");
        } catch (Exception e) { fallar("Frase() - " + e.getMessage()); }
    }

    static void testFraseConstructorTexto() {
        try {
            Frase f = new Frase("Carpe Diem");
            assert f.getTexto().equals("Carpe Diem");
            pasar("Frase(texto) - sobrecarga OK");
        } catch (Exception e) { fallar("Frase(texto) - " + e.getMessage()); }
    }

    static void testFraseConstructorCompleto() {
        try {
            Frase f = new Frase("Cogito ergo sum", "Descartes");
            assert f.getTexto().equals("Cogito ergo sum");
            assert f.getAutor().equals("Descartes");
            pasar("Frase(texto, autor) - sobrecarga OK");
        } catch (Exception e) { fallar("Frase(texto, autor) - " + e.getMessage()); }
    }

    static void testFraseToString() {
        try {
            Frase f = new Frase("La vida es bella", "Anonimo");
            String resultado = f.toString();
            assert resultado.contains("La vida es bella");
            assert resultado.contains("Anonimo");
            pasar("Frase toString() - OK");
        } catch (Exception e) { fallar("Frase toString() - " + e.getMessage()); }
    }

  
    static void testSobrecargaRegistrar() {
        try {
            UsuarioController c = new UsuarioController();
            c.getClass().getMethod("registrar", Usuario.class);
            c.getClass().getMethod("registrar", String.class, String.class, String.class);
            pasar("Sobrecarga registrar() en UsuarioController - OK");
        } catch (NoSuchMethodException e) { fallar("Sobrecarga registrar() - " + e.getMessage()); }
    }

    static void testSobrecargaLogin() {
        try {
            UsuarioController c = new UsuarioController();
            c.getClass().getMethod("login", String.class, String.class);
            c.getClass().getMethod("login", Usuario.class);
            pasar("Sobrecarga login() en UsuarioController - OK");
        } catch (NoSuchMethodException e) { fallar("Sobrecarga login() - " + e.getMessage()); }
    }

    static void testSobrecargaRegistrarEmocion() {
        try {
            EmocionController c = new EmocionController();
            c.getClass().getMethod("registrarEmocion", int.class, String.class, int.class, String.class);
            c.getClass().getMethod("registrarEmocion", int.class, String.class, int.class);
            c.getClass().getMethod("registrarEmocion", EstadoAnimo.class);
            pasar("Sobrecarga registrarEmocion() en EmocionController - OK");
        } catch (NoSuchMethodException e) { fallar("Sobrecarga registrarEmocion() - " + e.getMessage()); }
    }

    static void testSobrecargaObtenerHistorial() {
        try {
            EmocionController c = new EmocionController();
            c.getClass().getMethod("obtenerHistorial", int.class);
            c.getClass().getMethod("obtenerHistorial", int.class, int.class);
            pasar("Sobrecarga obtenerHistorial() en EmocionController - OK");
        } catch (NoSuchMethodException e) { fallar("Sobrecarga obtenerHistorial() - " + e.getMessage()); }
    }

    

    static void pasar(String msg) {
        System.out.println("  ✔ PASO  : " + msg);
        exitos++;
    }

    static void fallar(String msg) {
        System.out.println("  ✘ FALLO : " + msg);
        fallos++;
    }

    static void resumen() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║            RESUMEN FINAL             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf ("║  Pruebas exitosas  : %-16d║%n", exitos);
        System.out.printf ("║  Pruebas fallidas  : %-16d║%n", fallos);
        System.out.printf ("║  Total de pruebas  : %-16d║%n", exitos + fallos);
        System.out.println("╚══════════════════════════════════════╝");
    }
}