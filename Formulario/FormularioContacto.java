package formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class FormularioContacto extends JFrame{
  
    private static final int MAX_NOMBRE     = 20;
    private static final int MAX_APELLIDO   = 20;
    private static final int MAX_DNI        = 8;
    private static final int MAX_PASAPORTE  = 9;   
    private static final int MAX_TELEFONO   = 25;
    private static final int MAX_CP         = 4;
    private static final int MAX_DOMICILIO  = 50;

    
    private static final long NUM_MIN = 10_000_000L;
    private static final long NUM_MAX = 60_000_000L;

   
    private static final Pattern PATRON_PASAPORTE = Pattern.compile("^[A-Z]\\d{8}$");

   
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private JTextField txtPasaporte;
    private JTextField txtTelefono;
    private JTextField txtCodigoPostal;
    private JTextField txtDomicilio;
    private JButton    btnValidar;
    private JButton    btnLimpiar;
    private JButton    btnCerrar;

    public FormularioContacto() {
        super("Carga de contacto");
        configurarVentana();
        construirInterfaz();
        aplicarFiltrosDeCaracter();
        agregarValidacionesDeFoco();
        agregarExclusionDniPasaporte();
        agregarEventosBotones();
    }

   
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setSize(520, 430);
        setLocationRelativeTo(null);
        setResizable(false);
    }

   
    private void construirInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Carga de contacto",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        int fila = 0;

        txtNombre       = agregarFila(panelForm, gbc, fila++, "Nombre:",        25);
        txtApellido     = agregarFila(panelForm, gbc, fila++, "Apellido:",      25);
        txtDni          = agregarFila(panelForm, gbc, fila++, "DNI:",           25);
        txtPasaporte    = agregarFila(panelForm, gbc, fila++, "Pasaporte:",     25);
        txtTelefono     = agregarFila(panelForm, gbc, fila++, "Teléfono:",      25);
        txtCodigoPostal = agregarFila(panelForm, gbc, fila++, "Código Postal:", 25);
        txtDomicilio    = agregarFila(panelForm, gbc, fila++, "Domicilio:",     25);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnValidar = new JButton("Validar");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar  = new JButton("Cerrar");
        panelBotones.add(btnValidar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelForm,    BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    
    private JTextField agregarFila(JPanel panel, GridBagConstraints gbc, int fila, String etiqueta, int columnas) {
        gbc.gridx = 0; gbc.gridy = fila; gbc.weightx = 0;
        panel.add(new JLabel(etiqueta), gbc);
        JTextField campo = new JTextField(columnas);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(campo, gbc);
        return campo;
    }

   
    private void aplicarFiltrosDeCaracter() {
        ((AbstractDocument) txtNombre.getDocument())
                .setDocumentFilter(new FiltroAlfabetico(MAX_NOMBRE));

        ((AbstractDocument) txtApellido.getDocument())
                .setDocumentFilter(new FiltroAlfabetico(MAX_APELLIDO));

        ((AbstractDocument) txtDni.getDocument())
                .setDocumentFilter(new FiltroNumerico(MAX_DNI));

        ((AbstractDocument) txtPasaporte.getDocument())
                .setDocumentFilter(new FiltroPasaporte(MAX_PASAPORTE));

        ((AbstractDocument) txtTelefono.getDocument())
                .setDocumentFilter(new FiltroTelefono(MAX_TELEFONO));

        ((AbstractDocument) txtCodigoPostal.getDocument())
                .setDocumentFilter(new FiltroNumerico(MAX_CP));

        ((AbstractDocument) txtDomicilio.getDocument())
                .setDocumentFilter(new FiltroLongitud(MAX_DOMICILIO));
    }

  
    private void agregarValidacionesDeFoco() {

       
        txtDni.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) {
                String v = txtDni.getText().trim();
                if (!v.isEmpty() && !validarRangoNumerico(v)) {
                    mostrarError("El DNI debe ser un número entre "
                            + formato(NUM_MIN) + " y " + formato(NUM_MAX) + ".");
                    SwingUtilities.invokeLater(() -> txtDni.requestFocusInWindow());
                }
            }
        });

       
        txtPasaporte.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) {
                String v = txtPasaporte.getText().trim().toUpperCase();
                if (v.isEmpty()) return;

                if (!PATRON_PASAPORTE.matcher(v).matches()) {
                    mostrarError("El Pasaporte debe tener 1 letra (A-Z) seguida de 8 dígitos.\nEjemplo: N39392288");
                    SwingUtilities.invokeLater(() -> txtPasaporte.requestFocusInWindow());
                    return;
                }
                if (!validarRangoNumerico(v.substring(1))) {
                    mostrarError("La parte numérica del Pasaporte debe estar entre "
                            + formato(NUM_MIN) + " y " + formato(NUM_MAX) + ".");
                    SwingUtilities.invokeLater(() -> txtPasaporte.requestFocusInWindow());
                }
            }
        });

        txtTelefono.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) {
                String v = txtTelefono.getText().trim();
                if (!v.isEmpty()) {
                    long digitos = v.chars().filter(Character::isDigit).count();
                    if (digitos <= 6) {
                        mostrarError("El Teléfono debe contener más de 6 dígitos numéricos.\nEjemplo: +54 9 (261)-5-012345");
                        SwingUtilities.invokeLater(() -> txtTelefono.requestFocusInWindow());
                    }
                }
            }
        });

        txtCodigoPostal.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) {
                String v = txtCodigoPostal.getText().trim();
                if (!v.isEmpty() && v.length() != 4) {
                    mostrarError("El Código Postal debe tener exactamente 4 dígitos numéricos.");
                    SwingUtilities.invokeLater(() -> txtCodigoPostal.requestFocusInWindow());
                }
            }
        });
    }

   
    private void agregarExclusionDniPasaporte() {

        txtDni.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { actualizar(); }
            @Override public void removeUpdate(DocumentEvent e) { actualizar(); }
            @Override public void changedUpdate(DocumentEvent e) { actualizar(); }
            private void actualizar() {
                boolean dniLleno = !txtDni.getText().isEmpty();
                txtPasaporte.setEnabled(!dniLleno);
                txtPasaporte.setBackground(dniLleno
                        ? new Color(235, 235, 235)
                        : Color.WHITE);
            }
        });

        txtPasaporte.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { actualizar(); }
            @Override public void removeUpdate(DocumentEvent e) { actualizar(); }
            @Override public void changedUpdate(DocumentEvent e) { actualizar(); }
            private void actualizar() {
                boolean pasaporteLleno = !txtPasaporte.getText().isEmpty();
                txtDni.setEnabled(!pasaporteLleno);
                txtDni.setBackground(pasaporteLleno
                        ? new Color(235, 235, 235)
                        : Color.WHITE);
            }
        });
    }

    private void agregarEventosBotones() {
        btnValidar.addActionListener(e -> validarFormulario());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> cerrarFormulario());
    }

   
    private void validarFormulario() {

        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarError("El campo Nombre es obligatorio.");
            txtNombre.requestFocusInWindow();
            return;
        }

        String apellido = txtApellido.getText().trim();
        if (apellido.isEmpty()) {
            mostrarError("El campo Apellido es obligatorio.");
            txtApellido.requestFocusInWindow();
            return;
        }

        String dni       = txtDni.getText().trim();
        String pasaporte = txtPasaporte.getText().trim().toUpperCase();
        boolean hayDni       = !dni.isEmpty();
        boolean hayPasaporte = !pasaporte.isEmpty();

        if (hayDni && hayPasaporte) {
            mostrarError("Solo puede completar UNO de los campos: DNI o Pasaporte.");
            return;
        }
        if (!hayDni && !hayPasaporte) {
            mostrarError("Debe completar el DNI o el Pasaporte.");
            txtDni.requestFocusInWindow();
            return;
        }

        if (hayDni && !validarRangoNumerico(dni)) {
            mostrarError("El DNI debe ser un número entre "
                    + formato(NUM_MIN) + " y " + formato(NUM_MAX) + ".");
            txtDni.requestFocusInWindow();
            return;
        }

        if (hayPasaporte) {
            if (!PATRON_PASAPORTE.matcher(pasaporte).matches()) {
                mostrarError("El Pasaporte debe tener 1 letra (A-Z) seguida de 8 dígitos.\nEjemplo: N39392288");
                txtPasaporte.requestFocusInWindow();
                return;
            }
            if (!validarRangoNumerico(pasaporte.substring(1))) {
                mostrarError("La parte numérica del Pasaporte debe estar entre "
                        + formato(NUM_MIN) + " y " + formato(NUM_MAX) + ".");
                txtPasaporte.requestFocusInWindow();
                return;
            }
        }

        String telefono = txtTelefono.getText().trim();
        if (telefono.isEmpty()) {
            mostrarError("El campo Teléfono es obligatorio.");
            txtTelefono.requestFocusInWindow();
            return;
        }
        long digitos = telefono.chars().filter(Character::isDigit).count();
        if (digitos <= 6) {
            mostrarError("El Teléfono debe contener más de 6 dígitos numéricos.\nEjemplo: +54 9 (261)-5-012345");
            txtTelefono.requestFocusInWindow();
            return;
        }

        String cp = txtCodigoPostal.getText().trim();
        if (cp.isEmpty()) {
            mostrarError("El campo Código Postal es obligatorio.");
            txtCodigoPostal.requestFocusInWindow();
            return;
        }
        if (cp.length() != 4) {
            mostrarError("El Código Postal debe tener exactamente 4 dígitos numéricos.");
            txtCodigoPostal.requestFocusInWindow();
            return;
        }

        String domicilio = txtDomicilio.getText().trim();
        if (domicilio.isEmpty()) {
            mostrarError("El campo Domicilio es obligatorio.");
            txtDomicilio.requestFocusInWindow();
            return;
        }

        
        StringBuilder resumen = new StringBuilder("Formulario validado correctamente.\n\n");
        resumen.append("Nombre: ").append(nombre).append('\n');
        resumen.append("Apellido: ").append(apellido).append('\n');
        if (hayDni)        resumen.append("DNI: ").append(dni).append('\n');
        if (hayPasaporte)  resumen.append("Pasaporte: ").append(pasaporte).append('\n');
        resumen.append("Teléfono: ").append(telefono).append('\n');
        resumen.append("Código Postal: ").append(cp).append('\n');
        resumen.append("Domicilio: ").append(domicilio);

        JOptionPane.showMessageDialog(this,
                resumen.toString(),
                "Validación exitosa",
                JOptionPane.INFORMATION_MESSAGE);
    }

    
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtPasaporte.setText("");
        txtTelefono.setText("");
        txtCodigoPostal.setText("");
        txtDomicilio.setText("");

        txtDni.setEnabled(true);
        txtPasaporte.setEnabled(true);
        txtDni.setBackground(Color.WHITE);
        txtPasaporte.setBackground(Color.WHITE);

        txtNombre.requestFocusInWindow();
    }

    private void cerrarFormulario() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea cerrar el formulario?",
                "Confirmar cierre",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private boolean validarRangoNumerico(String s) {
        try {
            long n = Long.parseLong(s);
            return n >= NUM_MIN && n <= NUM_MAX;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String formato(long n) {
        return String.format("%,d", n).replace(',', '.');
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje,
                "Error de validación", JOptionPane.ERROR_MESSAGE);
    }


    
    private static class FiltroAlfabetico extends DocumentFilter {
        private final int max;
        FiltroAlfabetico(int max) { this.max = max; }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if (esAlfabetico(s) && (fb.getDocument().getLength() + s.length()) <= max) {
                super.insertString(fb, offset, s, a);
            }
        }
        
        
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if (esAlfabetico(s) && (fb.getDocument().getLength() - length + s.length()) <= max) {
                super.replace(fb, offset, length, s, a);
            }
        }
        private boolean esAlfabetico(String s) {
            return s.matches("[a-zA-ZÃ¡Ã©Ã­Ã³ÃºÃ�Ã‰Ã�Ã“ÃšÃ±Ã‘Ã¼Ãœ ]*");
        }
    }
    

    private static class FiltroNumerico extends DocumentFilter {
        private final int max;
        FiltroNumerico(int max) { this.max = max; }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if (s.matches("\\d*") && (fb.getDocument().getLength() + s.length()) <= max) {
                super.insertString(fb, offset, s, a);
            }
        }
        
        
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if (s.matches("\\d*") && (fb.getDocument().getLength() - length + s.length()) <= max) {
                super.replace(fb, offset, length, s, a);
            }
        }
    }

    private static class FiltroPasaporte extends DocumentFilter {
        private final int max;
        FiltroPasaporte(int max) { this.max = max; }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            String upper = s.toUpperCase();
            String actual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String resultado = new StringBuilder(actual).insert(offset, upper).toString();
            if (esConstruccionValida(resultado) && resultado.length() <= max) {
                super.insertString(fb, offset, upper, a);
            }
        }
        
        
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            String upper = s.toUpperCase();
            String actual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String resultado = new StringBuilder(actual).replace(offset, offset + length, upper).toString();
            if (esConstruccionValida(resultado) && resultado.length() <= max) {
                super.replace(fb, offset, length, upper, a);
            }
        }
        private boolean esConstruccionValida(String s) {
            if (s.isEmpty()) return true;
            if (!s.substring(0, 1).matches("[A-Z]")) return false;
            if (s.length() > 1) return s.substring(1).matches("\\d*");
            return true;
        }
    }

  
    private static class FiltroTelefono extends DocumentFilter {
        private final int max;
        FiltroTelefono(int max) { this.max = max; }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if (esTelefono(s) && (fb.getDocument().getLength() + s.length()) <= max) {
                super.insertString(fb, offset, s, a);
            }
        }
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if (esTelefono(s) && (fb.getDocument().getLength() - length + s.length()) <= max) {
                super.replace(fb, offset, length, s, a);
            }
        }
        private boolean esTelefono(String s) {
            return s.matches("[0-9+()\\- ]*");
        }
    }

   
    private static class FiltroLongitud extends DocumentFilter {
        private final int max;
        FiltroLongitud(int max) { this.max = max; }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if ((fb.getDocument().getLength() + s.length()) <= max) {
                super.insertString(fb, offset, s, a);
            }
        }
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String s, AttributeSet a) throws BadLocationException {
            if (s == null) return;
            if ((fb.getDocument().getLength() - length + s.length()) <= max) {
                super.replace(fb, offset, length, s, a);
            }
        }
    } 
}
