package ej5_dialogos;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class VentanaDialogos extends JFrame{
     private JLabel lblResultado;

    public VentanaDialogos() {
        super("EJ5 - JOptionPane");

        setSize(560, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        lblResultado = new JLabel("Resultado: sin acciones todavía", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton btnMensaje = new JButton("Mostrar mensaje");
        JButton btnEntrada = new JButton("Pedir nombre");
        JButton btnConfirmar = new JButton("Confirmar acción");
        JButton btnError = new JButton("Mostrar error");

        btnMensaje.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Este es un mensaje informativo.", "Información", JOptionPane.INFORMATION_MESSAGE));

        btnEntrada.addActionListener(e -> pedirNombre());

        btnConfirmar.addActionListener(e -> confirmar());

        btnError.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Ejemplo de mensaje de error.", "Error", JOptionPane.ERROR_MESSAGE));

        panel.add(lblResultado);
        panel.add(btnMensaje);
        panel.add(btnEntrada);
        panel.add(btnConfirmar);
        panel.add(btnError);

        add(panel);
    }

    private void pedirNombre() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre:", "Usuario");
        if (nombre != null && !nombre.trim().isEmpty()) {
            lblResultado.setText("Resultado: Hola, " + nombre.trim());
        }
    }

    private void confirmar() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea guardar los cambios?", "Confirmación", JOptionPane.YES_NO_OPTION);
        lblResultado.setText(opcion == JOptionPane.YES_OPTION ? "Resultado: cambios guardados" : "Resultado: acción cancelada");
    }
}
