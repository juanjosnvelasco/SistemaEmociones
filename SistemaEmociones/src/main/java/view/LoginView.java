/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author juanc
 */

import controller.UsuarioController;
import model.Usuario;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private UsuarioController controller = new UsuarioController();

    private JLabel lblTitulo    = new JLabel("💚 InnerMood", SwingConstants.CENTER);
    private JLabel lblEmail     = new JLabel("Email:");
    private JLabel lblContrasena= new JLabel("Contraseña:");
    private JTextField txtEmail = new JTextField(20);
    private JPasswordField txtContrasena = new JPasswordField(20);
    private JButton btnLogin    = new JButton("Iniciar Sesión");
    private JButton btnRegistro = new JButton("¿No tienes cuenta? Regístrate");

    public LoginView() {
        setTitle("InnerMood - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        add(lblTitulo, g);

        g.gridwidth = 1;
        g.gridx = 0; g.gridy = 1; add(lblEmail, g);
        g.gridx = 1; g.gridy = 1; add(txtEmail, g);

        g.gridx = 0; g.gridy = 2; add(lblContrasena, g);
        g.gridx = 1; g.gridy = 2; add(txtContrasena, g);

        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        add(btnLogin, g);

        g.gridy = 4;
        add(btnRegistro, g);

        // Acción Login
        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String pass  = new String(txtContrasena.getPassword());
            Usuario u = controller.login(email, pass);
            if (u != null) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido, " + u.getNombre() + "!");
                new DashboardView(u).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción Registro
        btnRegistro.addActionListener(e -> {
            new RegistroView().setVisible(true);
            dispose();
        });
    }
}