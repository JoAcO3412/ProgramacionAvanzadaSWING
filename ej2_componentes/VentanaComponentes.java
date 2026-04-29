package ej2_componentes;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VentanaComponentes extends JFrame{
     private JTextField txtNombre;
    private JCheckBox chkTerminos;
    private JRadioButton rbAlumno;
    private JRadioButton rbDocente;

    public VentanaComponentes() {
        super("EJ2 - Componentes Swing");

        setSize(560, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Formulario de registro", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        txtNombre = new JTextField(20);
        chkTerminos = new JCheckBox("Acepto las condiciones de uso");

        rbAlumno = new JRadioButton("Alumno", true);
        rbDocente = new JRadioButton("Docente");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbAlumno);
        grupo.add(rbDocente);

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRadio.add(rbAlumno);
        panelRadio.add(rbDocente);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrar());

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        principal.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        principal.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        principal.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        principal.add(new JLabel("Tipo:"), gbc);

        gbc.gridx = 1;
        principal.add(panelRadio, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        principal.add(chkTerminos, gbc);

        gbc.gridy = 4;
        principal.add(btnRegistrar, gbc);

        add(principal);
    }

    private void registrar() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!chkTerminos.isSelected()) {
            JOptionPane.showMessageDialog(this, "Debe aceptar las condiciones.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tipo = rbAlumno.isSelected() ? "Alumno" : "Docente";
        JOptionPane.showMessageDialog(this, "Registro correcto\nNombre: " + nombre + "\nTipo: " + tipo);
    }
}
