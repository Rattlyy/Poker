package gioco.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Macchina extends JPanel {
    public Macchina() {
        super();
        setSize(new Dimension(240, 100));
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLUE);
        g2.fill(new Rectangle2D.Double(10, 20, 200, 50));
        g2.fill(new RoundRectangle2D.Double(0, 0, 220, 40, 10, 10));

        g2.setColor(Color.CYAN);
        g2.fill(new Rectangle2D.Double(40, 5, 40, 30));
        g2.fill(new Rectangle2D.Double(140, 5, 40, 30));

        g2.setColor(Color.BLACK);
        g2.fill(new Ellipse2D.Double(20, 60, 30, 30));
        g2.fill(new Ellipse2D.Double(170, 60, 30, 30));

        g2.setColor(Color.YELLOW);
        g2.fill(new Ellipse2D.Double(5, 15, 10, 10));
        g2.fill(new Ellipse2D.Double(205, 15, 10, 10));

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(80, 65, 60, 15));

        g2.setColor(Color.BLACK); g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("SISAL", 94, 77);
    }
}
