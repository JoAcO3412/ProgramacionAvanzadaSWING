package formulario;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Formulario {

  
    public static void main(String[] args) {
       // Aplicar el Look & Feel del sistema operativo (opcional, mejora apariencia)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, continÃºa con el Look & Feel por defecto
        }

        // Lanzar la ventana en el Event Dispatch Thread (buena prÃ¡ctica de Swing)
        SwingUtilities.invokeLater(() -> {
            FormularioContacto formulario = new FormularioContacto();
            formulario.setVisible(true);
        });
    }
 }
