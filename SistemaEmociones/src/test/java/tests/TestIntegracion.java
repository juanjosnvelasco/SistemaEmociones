/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tests;
/**
 *
 * @author juanc
 */
import controller.EmocionController;
import controller.UsuarioController;
import database.ConexionDB;
import database.EstadoAnimoDAO;
import database.RecomendacionDAO;
import database.UsuarioDAO;
import model.EstadoAnimo;
import model.Recomendacion;
import model.Usuario;
import java.sql.Connection;
import java.util.List;

public class TestIntegracion {

    static int exitos = 0;
    static int fallos = 0;

  
    static String EMAIL_TEST    = "J7an2244@gmail.com";
    static String PASSWORD_TEST = "12345678910";

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   PRUEBAS DE INTEGRACION - EmoAdapt  ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        testConexionMySQL();
        testLoginUsuarioExistente();
        testLoginUsuarioInexistente();
        testRegistrarYBuscarUsuario();
        testGuardarEstadoAnimo();
        testObtenerHistorialConDatos();
        testObtenerHistorialLimitado();
        testRecomendacionPorEmocion();
        testRecomendacionEmocionesDiferentes();
        testFlujoCompletoUsuario();

        resumen();
    }

    

    static void testConexionMySQL() {
        System.out.println("TEST 1: Conexion a MySQL");
        try {
            Connection con = ConexionDB.getConexion();
            assert con != null : "Conexion nula";
            assert !con.isClosed() : "Conexion cerrada";
            con.close();
            pasar("Conexion a MySQL establecida correctamente");
        } catch (Exception e) {
            fallar("Conexion fallida: " + e.getMessage());
        }
    }

    

    static void testLoginUsuarioExistente() {
        System.out.println("\nTEST 2: Login con usuario existente");
        try {
            UsuarioController controller = new UsuarioController();
            Usuario u = controller.login(EMAIL_TEST, PASSWORD_TEST);
            assert u != null : "Usuario no encontrado";
            assert u.getNombre() != null : "Nombre vacio";
            assert u.getId() > 0 : "ID invalido";
            pasar("Login exitoso para: " + u.getNombre() + " (ID: " + u.getId() + ")");
        } catch (Exception e) {
            fallar("Login fallo: " + e.getMessage());
        }
    }

    

    static void testLoginUsuarioInexistente() {
        System.out.println("\nTEST 3: Login con credenciales incorrectas");
        try {
            UsuarioController controller = new UsuarioController();
            Usuario u = controller.login("noexiste@test.com", "wrongpass");
            assert u == null : "Debio retornar null";
            pasar("Login incorrecto retorna null correctamente");
        } catch (Exception e) {
            fallar("Error inesperado: " + e.getMessage());
        }
    }

    

    static void testRegistrarYBuscarUsuario() {
        System.out.println("\nTEST 4: Registro de nuevo usuario");
        try {
            UsuarioController controller = new UsuarioController();
            String emailTest = "testuser_" + System.currentTimeMillis() + "@test.com";
            boolean ok = controller.registrar("Usuario Test", emailTest, "test123");
            assert ok : "Registro fallo";

            Usuario u = controller.login(emailTest, "test123");
            assert u != null : "No se encontro el usuario recien registrado";
            assert u.getNombre().equals("Usuario Test");
            pasar("Registro y verificacion exitosos para: " + emailTest);
        } catch (Exception e) {
            fallar("Error en registro: " + e.getMessage());
        }
    }

    

    static void testGuardarEstadoAnimo() {
        System.out.println("\nTEST 5: Guardar estado emocional");
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(EMAIL_TEST, PASSWORD_TEST);
            assert u != null : "Usuario no encontrado para el test";

            EmocionController ec = new EmocionController();
            boolean ok = ec.registrarEmocion(u.getId(), "Motivado", 9, "Test de integracion");
            assert ok : "No se guardo el estado de animo";
            pasar("Estado emocional guardado correctamente en MySQL");
        } catch (Exception e) {
            fallar("Error al guardar: " + e.getMessage());
        }
    }

    
    static void testObtenerHistorialConDatos() {
        System.out.println("\nTEST 6: Obtener historial emocional");
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(EMAIL_TEST, PASSWORD_TEST);

            EmocionController ec = new EmocionController();
            List<EstadoAnimo> lista = ec.obtenerHistorial(u.getId());
            assert lista != null : "Lista nula";
            assert lista.size() > 0 : "Historial vacio";

            EstadoAnimo primero = lista.get(0);
            assert primero.getEmocion() != null : "Emocion nula";
            assert primero.getFecha() != null : "Fecha nula";
            pasar("Historial obtenido: " + lista.size() + " registros encontrados");
        } catch (Exception e) {
            fallar("Error en historial: " + e.getMessage());
        }
    }

    

    static void testObtenerHistorialLimitado() {
        System.out.println("\nTEST 7: Historial limitado (sobrecarga)");
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(EMAIL_TEST, PASSWORD_TEST);

            EmocionController ec = new EmocionController();
            List<EstadoAnimo> lista = ec.obtenerHistorial(u.getId(), 3);
            assert lista != null : "Lista nula";
            assert lista.size() <= 3 : "Supero el limite de 3";
            pasar("Historial limitado a 3 registros: OK (" + lista.size() + " obtenidos)");
        } catch (Exception e) {
            fallar("Error en historial limitado: " + e.getMessage());
        }
    }

    
    static void testRecomendacionPorEmocion() {
        System.out.println("\nTEST 8: Recomendacion por emocion");
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(EMAIL_TEST, PASSWORD_TEST);

            RecomendacionDAO dao = new RecomendacionDAO();
            Recomendacion r = dao.obtenerRecomendacionDelDia(u.getId(), "Estresado");

            if (r != null) {
                assert r.getActividad() != null : "Actividad nula";
                assert r.getExplicacionPsicologica() != null : "Explicacion nula";
                pasar("Recomendacion obtenida: " + r.getActividad());
            } else {
                pasar("Ya se vieron todas las recomendaciones de hoy para Estresado");
            }
        } catch (Exception e) {
            fallar("Error en recomendacion: " + e.getMessage());
        }
    }

    
    static void testRecomendacionEmocionesDiferentes() {
        System.out.println("\nTEST 9: Recomendaciones para emociones diferentes");
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(EMAIL_TEST, PASSWORD_TEST);
            RecomendacionDAO dao = new RecomendacionDAO();

            String[] emociones = {"Alegre", "Triste", "Ansioso", "Enojado"};
            int encontradas = 0;

            for (String emocion : emociones) {
                Recomendacion r = dao.obtenerRecomendacionDelDia(u.getId(), emocion);
                if (r != null) encontradas++;
            }

            pasar("Recomendaciones obtenidas para " + encontradas + " de 4 emociones");
        } catch (Exception e) {
            fallar("Error en emociones multiples: " + e.getMessage());
        }
    }

    
    static void testFlujoCompletoUsuario() {
        System.out.println("\nTEST 10: Flujo completo usuario");
        try {
            // 1. Login
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(EMAIL_TEST, PASSWORD_TEST);
            assert u != null : "Login fallo";

            // 2. Registrar emocion
            EmocionController ec = new EmocionController();
            boolean guardado = ec.registrarEmocion(u.getId(), "Cansado", 6, "Flujo completo test");
            assert guardado : "No se guardo la emocion";

            // 3. Verificar en historial
            List<EstadoAnimo> historial = ec.obtenerHistorial(u.getId());
            assert historial.size() > 0 : "Historial vacio";

            // 4. Obtener recomendacion
            RecomendacionDAO dao = new RecomendacionDAO();
            Recomendacion r = dao.obtenerRecomendacionDelDia(u.getId(), "Cansado");

            pasar("Flujo completo exitoso: Login → Registro → Historial → Recomendacion");
        } catch (Exception e) {
            fallar("Flujo completo fallo: " + e.getMessage());
        }
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