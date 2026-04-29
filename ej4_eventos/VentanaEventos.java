package ej4_eventos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class VentanaEventos extends JFrame{
    private JTextField txtTexto;
    private JLabel lblContador;
    private JLabel lblMouse;
    private JTextArea areaEventos;

    public VentanaEventos() {
        super("EJ4 - Manejo de eventos");

        setSize(680, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel superior = new JPanel(new GridLayout(3, 1, 5, 5));
        txtTexto = new JTextField();
        lblContador = new JLabel("Caracteres escritos: 0");
        lblMouse = new JLabel("Movimiento del mouse: sin datos");

        superior.add(new JLabel("Escriba un texto para activar DocumentListener:"));
        superior.add(txtTexto);
        superior.add(lblContador);

        areaEventos = new JTextArea();
        areaEventos.setEditable(false);
        areaEventos.setFont(new Font("Consolas", Font.PLAIN, 13));

        JButton btnClick = new JButton("Pulsame");
        btnClick.addActionListener(e -> registrarEvento("Se presionó el botón."));

        txtTexto.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarContador(); }
            public void removeUpdate(DocumentEvent e) { actualizarContador(); }
            public void changedUpdate(DocumentEvent e) { actualizarContador(); }
        });

        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                registrarEvento("Ventana abierta.");
            }
        });

        principal.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                lblMouse.setText("Movimiento del mouse: X=" + e.getX() + " Y=" + e.getY());
            }
        });

        JPanel inferior = new JPanel(new BorderLayout());
        inferior.add(lblMouse, BorderLayout.NORTH);
        inferior.add(btnClick, BorderLayout.SOUTH);

        principal.add(superior, BorderLayout.NORTH);
        principal.add(new JScrollPane(areaEventos), BorderLayout.CENTER);
        principal.add(inferior, BorderLayout.SOUTH);

        add(principal);
    }

    private void actualizarContador() {
        lblContador.setText("Caracteres escritos: " + txtTexto.getText().length());
        registrarEvento("Texto modificado.");
    }

    private void registrarEvento(String mensaje) {
        areaEventos.append(mensaje + "\n");
    }
}
