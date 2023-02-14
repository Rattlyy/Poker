package gioco.frames;

import gioco.enums.Win;
import gioco.components.Macchina;

import javax.swing.*;
import java.awt.event.*;

public class DialogWin extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel vinto;
    private Macchina macchina1;

    private void createUIComponents() {
        macchina1 = new Macchina();
    }

    public DialogWin(Win win) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        vinto.setText(vinto.getText().replace("%RESULT%", win.name()));

        buttonOK.addActionListener(e -> dispose());
        buttonCancel.addActionListener(e -> dispose());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void avvia(Win win) {
        DialogWin dialog = new DialogWin(win);
        dialog.pack();
        dialog.setVisible(true);
    }
}
