package ej3_layouts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VentanaLayouts extends JFrame{
    public VentanaLayouts() {
        super("EJ3 - Layouts + JPanel");

        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel encabezado = new JLabel("Panel de usuario con distintos Layout Managers", SwingConstants.CENTER);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 22));
        encabezado.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos personales"));
        formulario.add(new JLabel("Nombre:"));
        formulario.add(new JTextField());
        formulario.add(new JLabel("Apellido:"));
        formulario.add(new JTextField());
        formulario.add(new JLabel("DNI:"));
        formulario.add(new JTextField());

        JPanel fecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fecha.add(new JTextField(2));
        fecha.add(new JLabel("/"));
        fecha.add(new JTextField(2));
        fecha.add(new JLabel("/"));
        fecha.add(new JTextField(4));
        formulario.add(new JLabel("Fecha de nacimiento:"));
        formulario.add(fecha);

        JPanel menuLateral = new JPanel(new GridLayout(4, 1, 8, 8));
        menuLateral.setBorder(BorderFactory.createTitledBorder("Opciones"));
        menuLateral.add(new JButton("Nuevo"));
        menuLateral.add(new JButton("Guardar"));
        menuLateral.add(new JButton("Editar"));
        menuLateral.add(new JButton("Eliminar"));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.add(new JButton("Aceptar"));
        botones.add(new JButton("Cancelar"));

        add(encabezado, BorderLayout.NORTH);
        add(formulario, BorderLayout.CENTER);
        add(menuLateral, BorderLayout.WEST);
        add(botones, BorderLayout.SOUTH);
    }
}
