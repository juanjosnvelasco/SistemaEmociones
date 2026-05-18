/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author juanc
 */
import controller.FraseController;
import controller.EmocionController;
import model.Usuario;
import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    private Usuario usuarioActual;
    private FraseController fraseController = new FraseController();
    private EmocionController emocionController = new EmocionController();

    private JLabel lblBienvenida    = new JLabel("", SwingConstants.CENTER);
    private JLabel lblSubtitulo     = new JLabel("¿Cómo te sientes hoy?", SwingConstants.CENTER);
    private JButton btnRegistrar    = new JButton("Registrar Emocion");
    private JButton btnHistorial    = new JButton("Ver Historial");
    private JButton btnFrase        = new JButton("Frase del Dia");
    private JButton btnCerrarSesion = new JButton("Cerrar Sesion");

    public DashboardView(Usuario u) {
        this.usuarioActual = u;

        setTitle("InnerMood  - Inicio");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Estilos
        lblBienvenida.setText("Hola, " + u.getNombre() + "!");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        lblBienvenida.setForeground(new Color(34, 139, 34));

        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        lblSubtitulo.setForeground(new Color(100, 100, 100));

        Dimension btnSize = new Dimension(280, 45);
        btnRegistrar.setPreferredSize(btnSize);
        btnHistorial.setPreferredSize(btnSize);
        btnFrase.setPreferredSize(btnSize);
        btnCerrarSesion.setPreferredSize(btnSize);

        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnHistorial.setFont(new Font("Arial", Font.BOLD, 14));
        btnFrase.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 13));

        btnRegistrar.setBackground(new Color(70, 180, 100));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);

        btnHistorial.setBackground(new Color(70, 130, 180));
        btnHistorial.setForeground(Color.WHITE);
        btnHistorial.setFocusPainted(false);

        btnFrase.setBackground(new Color(255, 165, 0));
        btnFrase.setForeground(Color.WHITE);
        btnFrase.setFocusPainted(false);

        btnCerrarSesion.setBackground(new Color(200, 60, 60));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);

        // Layout
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;

        g.gridy = 0; add(lblBienvenida, g);
        g.gridy = 1; add(lblSubtitulo, g);
        g.gridy = 2; add(btnRegistrar, g);
        g.gridy = 3; add(btnHistorial, g);
        g.gridy = 4; add(btnFrase, g);
        g.gridy = 5; add(btnCerrarSesion, g);

        // Acciones
        btnRegistrar.addActionListener(e -> {
            new RegistroEmocionView(usuarioActual).setVisible(true);
            dispose();
        });

        btnHistorial.addActionListener(e -> {
            new HistorialView(usuarioActual).setVisible(true);
            dispose();
        });

        btnFrase.addActionListener(e -> {
            btnFrase.setText("Cargando...");
            btnFrase.setEnabled(false);

            new Thread(() -> {
                String frase = fraseController.obtenerFrase("motivado");
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(
                        DashboardView.this,
                        frase,
                        "Frase del Dia",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    btnFrase.setText("Frase del Dia");
                    btnFrase.setEnabled(true);
                });
            }).start();
        });

        btnCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Seguro que deseas cerrar sesion?",
                "Cerrar Sesion",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginView().setVisible(true);
                dispose();
            }
        });
    }
}