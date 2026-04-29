package ej6_graficos;

import javax.swing.JFrame;


public class VentanaGraficos extends JFrame{
    public VentanaGraficos() {
        super("EJ6 - GrÃ¡ficos en Swing");

        setSize(720, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new PanelGraficos());
    }
}
