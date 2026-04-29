package ej6_graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class PanelGraficos extends JPanel{
    public PanelGraficos() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        g2.setColor(new Color(245, 247, 250));
        g2.fillRoundRect(30, 30, ancho - 60, alto - 60, 25, 25);

        g2.setColor(new Color(45, 52, 54));
        g2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        g2.drawString("Dibujo con Graphics2D", 60, 75);

        g2.setStroke(new BasicStroke(3));

        g2.setColor(new Color(52, 152, 219));
        g2.fillOval(80, 120, 120, 120);

        g2.setColor(new Color(46, 204, 113));
        g2.fillRect(250, 120, 150, 110);

        g2.setColor(new Color(231, 76, 60));
        int[] x = {500, 580, 420};
        int[] y = {120, 240, 240};
        g2.fillPolygon(x, y, 3);

        g2.setColor(new Color(44, 62, 80));
        g2.drawLine(70, 300, ancho - 70, 300);

        g2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        g2.drawString("Círculo", 105, 265);
        g2.drawString("Rectángulo", 285, 265);
        g2.drawString("Triángulo", 465, 265);
    }
}
