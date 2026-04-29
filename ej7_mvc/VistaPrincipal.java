package ej7_mvc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VistaPrincipal extends JFrame{
    private JComboBox<String> comboAcciones;
    private JTextField txtNombre;
    private JTextField txtNumero1;
    private JTextField txtNumero2;
    private JLabel lblContador;
    private JTextArea areaResultado;
    private JButton btnEjecutar;
    private JButton btnLimpiar;

    public VistaPrincipal() {
        super("EJ7 - Modelo MVC con selección de acciones");

        setSize(720, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel principal = new JPanel(new BorderLayout(12, 12));
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Ejemplo MVC: elegir acción desde la Vista", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createTitledBorder("Entrada de datos"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboAcciones = new JComboBox<>(new String[]{
                "Incrementar contador",
                "Decrementar contador",
                "Reiniciar contador",
                "Guardar nombre",
                "Sumar números",
                "Restar números",
                "Mostrar resumen del modelo"
        });

        txtNombre = new JTextField();
        txtNumero1 = new JTextField();
        txtNumero2 = new JTextField();
        lblContador = new JLabel("Contador actual: 0");

        agregarFila(formulario, gbc, 0, "Acción:", comboAcciones);
        agregarFila(formulario, gbc, 1, "Nombre:", txtNombre);
        agregarFila(formulario, gbc, 2, "Número 1:", txtNumero1);
        agregarFila(formulario, gbc, 3, "Número 2:", txtNumero2);
        agregarFila(formulario, gbc, 4, "Estado:", lblContador);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEjecutar = new JButton("Ejecutar acción");
        btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnEjecutar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaResultado.setText("Seleccione una acción y presione Ejecutar.\n");

        principal.add(titulo, BorderLayout.NORTH);
        principal.add(formulario, BorderLayout.CENTER);
        principal.add(panelBotones, BorderLayout.SOUTH);
        principal.add(new JScrollPane(areaResultado), BorderLayout.EAST);

        add(principal);
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila, String texto, Component componente) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(componente, gbc);
    }

    public String getAccionSeleccionada() {
        return (String) comboAcciones.getSelectedItem();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getNumero1() {
        return txtNumero1.getText();
    }

    public String getNumero2() {
        return txtNumero2.getText();
    }

    public JButton getBtnEjecutar() {
        return btnEjecutar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void mostrarContador(int valor) {
        lblContador.setText("Contador actual: " + valor);
    }

    public void mostrarResultado(String mensaje) {
        areaResultado.setText(mensaje);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtNumero1.setText("");
        txtNumero2.setText("");
        areaResultado.setText("Campos limpiados.\n");
    }
}
