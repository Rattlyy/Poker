package gioco.components;

import gioco.enums.Seme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static gioco.frames.Gioco.randomSeme;

public class Carta extends JPanel {
    private String immagine = "DIETRO.GIF";
    private Seme seme;
    private int num = -1;

    public Carta() {
        super();
        setSize(72, 97);
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(
                    ImageIO.read(new File("carte/" + immagine)),
                    0, 0,
                    this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void random() {
        Random random = new Random();

        seme = randomSeme();
        num = random.nextInt(10) + 1;
        immagine = num + seme.name() + ".JPG";

        repaint();
    }

    public Seme getSeme() {
        return seme;
    }

    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Carta carta)
            return seme == carta.seme && num == carta.num;
        else return false;
    }

    public void dietro() {
        immagine = "DIETRO.GIF";
        repaint();
    }
}
