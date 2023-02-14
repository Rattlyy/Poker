package gioco.frames;

import gioco.enums.Seme;
import gioco.enums.Win;
import gioco.components.Carta;

import javax.swing.*;
import java.util.*;

public class Gioco {
    private JButton giocaButton;
    private JPanel panel;
    private Carta carta1;
    private Carta carta3;
    private Carta carta2;
    private Carta carta4;
    private Carta carta5;
    private JButton cambia1;
    private JButton cambia2;
    private JButton cambia3;
    private JButton cambia4;
    private JButton cambia5;
    private JButton mostraCarte;
    private final List<Carta> carte = List.of(carta1, carta2, carta3, carta4, carta5);

    public Gioco() {
        cambiaListener(carta1, cambia1);
        cambiaListener(carta2, cambia2);
        cambiaListener(carta3, cambia3);
        cambiaListener(carta4, cambia4);
        cambiaListener(carta5, cambia5);

        mostraCarte.addActionListener(e -> {
            do {
                carta1.random();
                carta3.random();
                carta2.random();
                carta4.random();
                carta5.random();
            } while (carte.stream().distinct().count() != carte.size());

            cambia1.setEnabled(true);
            cambia2.setEnabled(true);
            cambia3.setEnabled(true);
            cambia4.setEnabled(true);
            cambia5.setEnabled(true);

            mostraCarte.setEnabled(false);
            giocaButton.setEnabled(true);
        });

        giocaButton.addActionListener(e -> {
            DialogWin.avvia(checkWin(carta1, carta3, carta2, carta4, carta5));

            mostraCarte.setEnabled(true);
            giocaButton.setEnabled(false);

            cambia1.setEnabled(false);
            cambia2.setEnabled(false);
            cambia3.setEnabled(false);
            cambia4.setEnabled(false);
            cambia5.setEnabled(false);

            for (Carta carta : carte) {
                carta.dietro();
            }
        });
    }

    private void cambiaListener(Carta i, JButton cambia) {
        cambia.addActionListener(e -> {
            do {
                i.random();
            } while (carte.stream().distinct().count() != carte.size());
            cambia.setEnabled(false);
        });
    }

    private void createUIComponents() {
        carta1 = new Carta();
        carta3 = new Carta();
        carta2 = new Carta();
        carta4 = new Carta();
        carta5 = new Carta();
    }

    public static Seme randomSeme() {
        Random random = new Random();

        return switch (random.nextInt(4) + 1) {
            case 1 -> Seme.CUORI;
            case 2 -> Seme.FIORI;
            case 3 -> Seme.PICCHE;
            case 4 -> Seme.QUADRI;
            default -> null;
        };
    }


    public static Win checkWin(Carta... carte) {
        int[] is = new int[11];

        for (Seme seme : Seme.values()) {
            if (Arrays.stream(carte).allMatch(e -> e.getSeme() == seme))
                return Win.COLORE;
        }

        for (Carta c : carte) {
            is[c.getNum()] += 1;
        }

        int doppie = 0;
        for (Integer i : is) {
            if (i == 2) doppie++;
            if (i == 3) return Win.TRIS;
            if (i == 5) return Win.POKER;
        }

        if (doppie != 0) {
            if (doppie == 1) return Win.COPPIA;
            else return Win.DOPPIA_COPPIA;
        }

        return Win.NIENTE;
    }

    public static Win old(Carta... carte) {
        Map<Seme, Integer[]> map = new HashMap<>();

        for (Seme s : Seme.values()) {
            if (Arrays.stream(carte).allMatch((e) -> s == e.getSeme())) return Win.COLORE;

            map.put(s, new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }

        for (Carta a : carte) {
            var array = map.get(a.getSeme());
            array[a.getNum() - 1] += 1;

            map.replace(a.getSeme(), array);
        }

        int doppie = 0;
        for (Integer[] val : map.values()) {
            for (int i = 0; i < 10; i++) {
                if (val[i] == 5) return Win.POKER;
                if (val[i] == 3) return Win.TRIS;
                if (val[i] == 2) doppie++;
            }
        }

        if (doppie == 1) return Win.COPPIA;
        if (doppie == 2) return Win.DOPPIA_COPPIA;

        return Win.NIENTE;
    }

    public JPanel panel() {
        return panel;
    }
}
