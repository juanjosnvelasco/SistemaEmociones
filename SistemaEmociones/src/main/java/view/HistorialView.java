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

    private JLabel lblTitulo = new JLabel("Tu Historial Emocional", SwingConstants.CENTER);
    private JButton btnVolver = new JButton("← Volver al Dashboard");

    private String[] columnas = {"Fecha", "Emoción", "Intensidad", "Nota"};
    private DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // tabla no editable
        }
    };
    private JTable tabla = new JTable(modelo);

    public HistorialView(Usuario u) {
        this.usuarioActual = u;

        setTitle("InnerMood - Mi Historial");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Estilo título
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        // Estilo tabla
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(70, 130, 180));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setSelectionBackground(new Color(173, 216, 230));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFillsViewportHeight(false);

        // Ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(250);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(550, 300));

        // Estilo botón
        btnVolver.setPreferredSize(new Dimension(550, 38));
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 13));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(12, 12, 12, 12);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;

        g.gridy = 0; add(lblTitulo, g);
        g.gridy = 1; g.fill = GridBagConstraints.BOTH; add(scroll, g);
        g.gridy = 2; g.fill = GridBagConstraints.HORIZONTAL; add(btnVolver, g);

        cargarHistorial();

        btnVolver.addActionListener(e -> {
            new DashboardView(usuarioActual).setVisible(true);
            dispose();
        });
    }

    private void cargarHistorial() {
        modelo.setRowCount(0);
        List<EstadoAnimo> lista = controller.obtenerHistorial(usuarioActual.getId());

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Aún no tienes registros emocionales.",
                "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (EstadoAnimo ea : lista) {
            modelo.addRow(new Object[]{
                ea.getFecha(),
                ea.getEmocion(),
                ea.getIntensidad() + "/10",
                ea.getNota() != null && !ea.getNota().isEmpty() ? ea.getNota() : "-"
            });
        }
    }
}