package br.calc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavBar extends JPanel {

    private final JButton tema = new JButton("tema");
    private final Calculator calc;
    private boolean dark = false;

    public NavBar(Calculator calc) {
        this.calc = calc;
        configNavBar();
    }

    private void configNavBar() {
        setPreferredSize(new Dimension(300, 30));
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        setLayout(layout);

        configTemaButom();
        add(tema);
    }

    private void configTemaButom() {
        tema.setFocusable(false);
        tema.setFont(new Font("Courier", Font.PLAIN, 12));
        tema.setBackground(new Color(255, 255, 255, 0));
        tema.setBorder(null);
        tema.setPreferredSize(new Dimension(65, 20));

        tema.addActionListener(e -> {
            dark = !dark;
            calc.setTemaDark(dark);
        });
    }
}
