/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemaemociones;

/**
 *
 * @author juanc
 */
import view.LoginView;
import javax.swing.SwingUtilities;

public class SistemaEmociones {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}