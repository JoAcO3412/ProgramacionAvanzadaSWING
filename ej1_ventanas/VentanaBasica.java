package ej1_ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;



public class VentanaBasica extends JFrame{
        public VentanaBasica() {
        super("EJ1 - Ventana básica");

        setSize(520, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setBackground(new Color(245, 247, 250));

        JLabel titulo = new JLabel("Mi primera ventana Swing", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel descripcion = new JLabel(
                "<html><center>Ejemplo mejorado de JFrame.<br>"
                + "Se configura tamaño, cierre, ubicación y diseño visual.</center></html>",
                SwingConstants.CENTER
        );

        JButton cerrar = new JButton("Cerrar ventana");
        cerrar.addActionListener(e -> dispose());

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(descripcion, BorderLayout.CENTER);
        panel.add(cerrar, BorderLayout.SOUTH);

        add(panel);
    }
}
