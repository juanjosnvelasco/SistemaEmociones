/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author juanc
 */


import controller.EmocionController;
import model.Usuario;
import javax.swing.*;
import java.awt.*;

public class RegistroEmocionView extends JFrame {

    private EmocionController controller = new EmocionController();
    private Usuario usuarioActual;

    private JLabel lblTitulo     = new JLabel(" ¿Cómo te sientes hoy?", SwingConstants.CENTER);
    private JLabel lblEmocion    = new JLabel("Emoción:");
    private JLabel lblIntensidad = new JLabel("Intensidad (1-10):");
    private JLabel lblNota       = new JLabel("Nota opcional:");
    private JLabel lblValor      = new JLabel("5");

    private JComboBox<String> cmbEmocion = new JComboBox<>(new String[]{
        "😊 Alegre", "😢 Triste", "😰 Ansioso", "😠 Enojado",
        "😤 Estresado", "😴 Cansado", "💪 Motivado", "😐 Neutral"
    });

    private JSlider sliderIntensidad = new JSlider(1, 10, 5);
    private JTextArea txtNota        = new JTextArea(3, 20);
    private JButton btnGuardar       = new JButton("✅ Guardar Registro");
    private JButton btnVolver        = new JButton("← Volver");

    public RegistroEmocionView(Usuario u) {
        this.usuarioActual = u;

        setTitle("InnerMood - Registrar Emoción");
        setSize(430, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        sliderIntensidad.setMajorTickSpacing(1);
        sliderIntensidad.setPaintTicks(true);
        sliderIntensidad.setPaintLabels(true);
        sliderIntensidad.addChangeListener(e ->
            lblValor.setText(String.valueOf(sliderIntensidad.getValue()))
        );

        txtNota.setLineWrap(true);
        txtNota.setWrapStyleWord(true);
        JScrollPane scrollNota = new JScrollPane(txtNota);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.gridwidth = 3; add(lblTitulo, g);
        g.gridwidth = 1;
        g.gridx = 0; g.gridy = 1; add(lblEmocion, g);
        g.gridx = 1; g.gridy = 1; g.gridwidth = 2; add(cmbEmocion, g);
        g.gridwidth = 1;
        g.gridx = 0; g.gridy = 2; add(lblIntensidad, g);
        g.gridx = 1; g.gridy = 2; add(sliderIntensidad, g);
        g.gridx = 2; g.gridy = 2; add(lblValor, g);
        g.gridx = 0; g.gridy = 3; add(lblNota, g);
        g.gridx = 1; g.gridy = 3; g.gridwidth = 2; add(scrollNota, g);
        g.gridwidth = 1;
        g.gridx = 0; g.gridy = 4; add(btnVolver, g);
        g.gridx = 1; g.gridy = 4; g.gridwidth = 2; add(btnGuardar, g);

        btnGuardar.addActionListener(e -> {
            String emocion = (String) cmbEmocion.getSelectedItem();
            int intensidad = sliderIntensidad.getValue();
            String nota    = txtNota.getText().trim();

            boolean ok = controller.registrarEmocion(
                usuarioActual.getId(), emocion, intensidad, nota
            );
            if (ok) {
                JOptionPane.showMessageDialog(this, "¡Emoción registrada! 💚");
                new DashboardView(usuarioActual).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            new DashboardView(usuarioActual).setVisible(true);
            dispose();
        });
    }
}