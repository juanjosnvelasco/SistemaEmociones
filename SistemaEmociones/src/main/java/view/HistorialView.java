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
import model.EstadoAnimo;
import model.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistorialView extends JFrame {

    private EmocionController controller = new EmocionController();
    private Usuario usuarioActual;

    private String[] columnas = {"Fecha", "Emocion", "Intensidad", "Nota"};
    private DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable tabla = new JTable(modelo);

    public HistorialView(Usuario u) {
        this.usuarioActual = u;

        setTitle("EmoAdapt - Mi Historial");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(34, 139, 34));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setSelectionBackground(new Color(198, 239, 206));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFillsViewportHeight(true);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(280);

        JScrollPane scroll = new JScrollPane(tabla);
        JLabel lblTitulo = new JLabel("Tu Historial Emocional", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(34, 139, 34));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        
        JLabel lblTotal = new JLabel("", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.ITALIC, 12));
        lblTotal.setForeground(new Color(100, 100, 100));

        // Boton volver
        JButton btnVolver = new JButton("Volver al Dashboard");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setBackground(new Color(34, 139, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(200, 38));

    
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnVolver);

        
        setLayout(new BorderLayout(10, 10));
        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // Cargar datos
        cargarHistorial(lblTotal);

        btnVolver.addActionListener(e -> {
            new DashboardView(usuarioActual).setVisible(true);
            dispose();
        });
    }

    private void cargarHistorial(JLabel lblTotal) {
        modelo.setRowCount(0);

        List<EstadoAnimo> lista = controller.obtenerHistorial(usuarioActual.getId());

        if (lista.isEmpty()) {
            lblTotal.setText("Aun no tienes registros emocionales.");
            return;
        }

        lblTotal.setText("Total de registros: " + lista.size());

        for (EstadoAnimo ea : lista) {
            modelo.addRow(new Object[]{
                ea.getFecha(),
                ea.getEmocion(),
                ea.getIntensidad() + "/10",
                (ea.getNota() != null && !ea.getNota().isEmpty()) ? ea.getNota() : "-"
            });
        }
    }
}