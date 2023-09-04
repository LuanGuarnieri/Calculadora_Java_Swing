package br.calc.view;

import br.calc.model.CoresSistema;
import br.calc.model.Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyBoard extends JPanel implements ActionListener {

    static final String TIPO_PARAO = "PADRAO";
    static final String TIPO_AUXILIAR = "AUXILIAR";
    static final String TIPO_OPERACAO = "OPERACAO";

    public KeyBoard() {
        configKeyBoard();
        generateButtons();
    }

    private void generateButtons() {
        int nums = 1;
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.weightx = 1;
        gb.weighty = 1;

        for(int i = 1; i <= 20; i ++) {
            if (i <= 3) {
                //auxiliares
                gererateAssistantButtons(i , gb);
            } else if(i % 4 == 0) {
                //operacao
                gererateOperationButtons(i, gb);

            } else {
                //padrao
                generateDefaultButtons(nums, gb);
                nums ++;
            }
        }

    }

    private void generateDefaultButtons(int num, GridBagConstraints gb) {
        int gridX, gridY;

        if (num <= 9) {

            if (num <=3) {
                gridY = 1;
                gridX = num -1;
            }
            else if(num <= 6) {
                gridY = 2;

                    if(num == 4) {
                        gridX = 0;
                    } else if (num == 5) {
                        gridX = 1;
                    } else {
                        gridX = 2;
                    }

            } else {
                gridY = 3;

                if(num == 7) {
                    gridX = 0;
                } else if (num == 8) {
                    gridX = 1;
                } else {
                    gridX = 2;
                }
            }

            newButton(String.valueOf(num), CoresSistema.PADRAO, TIPO_PARAO, gridX, gridY, gb);

        } else {
            gridY = 4;

            if (num == 10) {
                gridX = 0;
                gb.gridwidth = 2;
                newButton("0",  CoresSistema.PADRAO, TIPO_PARAO, gridX, gridY, gb);
                gb.gridwidth = 1;

            } else {
               if (num != 11) {
                   gridX = 2;
                   newButton(",",  CoresSistema.PADRAO, TIPO_PARAO, gridX, gridY, gb);
               }
            }
        }
    }

    private void gererateAssistantButtons(int num, GridBagConstraints gb) {

        switch (num) {
            case 1 -> newButton("AC", CoresSistema.AUXILIAR, TIPO_AUXILIAR, 0, 0, gb);
            case 2 -> newButton("+/-", CoresSistema.AUXILIAR, TIPO_AUXILIAR, 1, 0, gb);
            case 3 -> newButton("%", CoresSistema.AUXILIAR, TIPO_AUXILIAR, 2, 0, gb);
        }
    }

    private void gererateOperationButtons(int num, GridBagConstraints gb) {

        switch (num) {
            case 4 -> newButton("/", CoresSistema.OPERACAO, TIPO_OPERACAO, 3, 0, gb);
            case 8 -> newButton("X", CoresSistema.OPERACAO, TIPO_OPERACAO, 3, 1, gb);
            case 12 -> newButton("-", CoresSistema.OPERACAO, TIPO_OPERACAO, 3, 2, gb);
            case 16 -> newButton("+", CoresSistema.OPERACAO, TIPO_OPERACAO, 3, 3, gb);
            case 20 -> newButton("=", CoresSistema.OPERACAO, TIPO_OPERACAO, 3, 4, gb);
        }
    }

    private void newButton(String valor, Color cor, String tipo, int gridX, int gridY, GridBagConstraints gb) {
        gb.gridx = gridX;
        gb.gridy = gridY;

        Button btn = new Button(valor, cor, tipo);

        if (tipo.equalsIgnoreCase("operacao")) {
            btn.setForeground(Color.WHITE);
        }

        btn.addActionListener(this);
        add(btn, gb);
    }

    public void modoDark(boolean dark) {
        if (dark) {
            setBackground(CoresSistema.BACKGROUND_DARK);

        } else {
            setBackground(CoresSistema.BACKGROUND_PADRAO);
        }

        for (int i = 0; i < this.getComponents().length; i ++) {
            ((Button) this.getComponent(i)).temaDark(dark);
        }
    }

    //configurações
    private void configKeyBoard() {
        setLayout(new GridBagLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Button) {
            Button aux = (Button) ((Button) e.getSource());

            Memoria.getMemoria().processarComando(aux.getValor());
        }

        System.out.println();
    }
}
