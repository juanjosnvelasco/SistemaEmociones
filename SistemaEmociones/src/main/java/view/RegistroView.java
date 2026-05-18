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
import javax.swing.*;
import java.awt.*;

public class RegistroView extends JFrame {

    private UsuarioController controller = new UsuarioController();

    private JLabel lblTitulo     = new JLabel(" Crear Cuenta", SwingConstants.CENTER);
    private JLabel lblNombre     = new JLabel("Nombre:");
    private JLabel lblEmail      = new JLabel("Email:");
    private JLabel lblContrasena = new JLabel("Contraseña:");

    private JTextField txtNombre     = new JTextField(20);
    private JTextField txtEmail      = new JTextField(20);
    private JPasswordField txtContrasena = new JPasswordField(20);

    private JButton btnRegistrar = new JButton("Registrarse");
    private JButton btnVolver    = new JButton("¿Ya tienes cuenta? Inicia sesión");

    public RegistroView() {
        setTitle("InnerMood - Registro");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.gridwidth = 2; add(lblTitulo, g);

        g.gridwidth = 1;
        g.gridx = 0; g.gridy = 1; add(lblNombre, g);
        g.gridx = 1; g.gridy = 1; add(txtNombre, g);

        g.gridx = 0; g.gridy = 2; add(lblEmail, g);
        g.gridx = 1; g.gridy = 2; add(txtEmail, g);

        g.gridx = 0; g.gridy = 3; add(lblContrasena, g);
        g.gridx = 1; g.gridy = 3; add(txtContrasena, g);

        g.gridx = 0; g.gridy = 4; g.gridwidth = 2; add(btnRegistrar, g);
        g.gridy = 5; add(btnVolver, g);

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String email  = txtEmail.getText().trim();
            String pass   = new String(txtContrasena.getPassword());

            if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean ok = controller.registrar(nombre, email, pass);
            if (ok) {
                JOptionPane.showMessageDialog(this, "¡Cuenta creada exitosamente!");
                new LoginView().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar. El email ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }
}