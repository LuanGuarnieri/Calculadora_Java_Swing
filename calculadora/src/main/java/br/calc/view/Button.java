package br.calc.view;

import br.calc.model.CoresSistema;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    private final String tipo;
    private Color COR_BOTAO;

    public Button(String valor, Color cor, String tipo) {
        setText(valor);
        this.COR_BOTAO = cor;
        this.tipo = tipo.toUpperCase();

        configButton();
    }

    public String getValor() {
        return this.getText();
    }

    public void setCOR_BOTAO(Color cor) {
        this.COR_BOTAO = cor;
    }

    public void temaDark(boolean dark) {
        if(dark) {
            setBorder(BorderFactory.createLineBorder(CoresSistema.BACKGROUND_DARK));

            if(tipo == KeyBoard.TIPO_PARAO) {
                setBackground(CoresSistema.PADRAO_DARK);
                setForeground(CoresSistema.TEXTO_PADRAO_DARK);
                setCOR_BOTAO(CoresSistema.PADRAO_DARK);

            } else if (tipo == KeyBoard.TIPO_AUXILIAR) {
               setForeground(CoresSistema.TEXTO_PADRAO_DARK);
               setBackground(CoresSistema.AUXILIAR_DARK);
               setCOR_BOTAO(CoresSistema.AUXILIAR_DARK);
            }

        } else {
            setBorder(BorderFactory.createLineBorder(CoresSistema.BACKGROUND_PADRAO));
            if(tipo == KeyBoard.TIPO_PARAO) {
                setBackground(CoresSistema.PADRAO);
                setForeground(CoresSistema.TEXTO_PADRAO);
                setCOR_BOTAO(CoresSistema.PADRAO);

            } else if (tipo == KeyBoard.TIPO_AUXILIAR) {
                setForeground(CoresSistema.TEXTO_PADRAO);
                setBackground(CoresSistema.AUXILIAR);
                setCOR_BOTAO(CoresSistema.AUXILIAR);
            }
        }
    }

    //configurações
    private void configButton() {
        setFocusable(false);
        setBackground(COR_BOTAO);
        setFont(new Font("Courier", Font.PLAIN, 25));
    }
}
