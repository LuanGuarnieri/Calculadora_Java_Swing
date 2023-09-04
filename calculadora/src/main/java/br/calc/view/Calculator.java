package br.calc.view;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private final Display DISPLAY = new Display(this);
    private final KeyBoard KEY_BOARD = new KeyBoard();

    public Calculator() {
        config();
        setTemaDark(false);
    }


    //configurações

    private void initComponents() {
        setLayout(new BorderLayout());
        add(DISPLAY, BorderLayout.NORTH);
        add(KEY_BOARD, BorderLayout.CENTER);
    }
    private void config() {
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    protected void setTemaDark(boolean dark) {
        setVisible(false);
        DISPLAY.modoDark(dark);
        KEY_BOARD.modoDark(dark);
        repaint();
        setVisible(true);
    }
}
