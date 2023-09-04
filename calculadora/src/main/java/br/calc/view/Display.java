package br.calc.view;

import br.calc.model.CoresSistema;
import br.calc.model.Memoria;
import br.calc.model.MemoriaObserver;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements MemoriaObserver {

    private final JLabel display = new JLabel(Memoria.getMemoria().getTextDisplay());
    private final NavBar NAV_BAR;
    public Display(Calculator calc) {
        Memoria.getMemoria().addObserver(this);
        NAV_BAR = new NavBar(calc);
        configLabel();
        configNavBar();
    }

    public void modoDark(boolean dark) {
        if (dark) {
            setBackground(CoresSistema.PADRAO_DARK);
            display.setForeground(CoresSistema.TEXTO_PADRAO_DARK);
            NAV_BAR.setBackground(CoresSistema.PADRAO_DARK);
            NAV_BAR.getComponent(0).setForeground(CoresSistema.TEXTO_PADRAO_DARK);

        } else {
            setBackground(CoresSistema.PADRAO);
            display.setForeground(CoresSistema.TEXTO_PADRAO);
            NAV_BAR.setBackground(CoresSistema.PADRAO);
            NAV_BAR.getComponent(0).setForeground(CoresSistema.TEXTO_PADRAO);
        }
    }

    private void configLabel() {
        setPreferredSize(new Dimension(300, 90));
        setLayout(new BorderLayout());
        display.setFont(new Font("Courier", Font.PLAIN, 40));
        display.setForeground(Color.BLACK);

        add(display, BorderLayout.EAST);
    }

    private void configNavBar() {
        add(NAV_BAR, BorderLayout.NORTH);
    }

    @Override
    public void valorAlterado(String novoValor) {
        display.setText(novoValor);
    }
}
