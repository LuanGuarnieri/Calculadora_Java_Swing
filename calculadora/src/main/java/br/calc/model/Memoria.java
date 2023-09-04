package br.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static final Memoria memoria = new Memoria();
    private final List<MemoriaObserver> observers = new ArrayList<>();
    private String textDisplay = "";
    private String buffer = "";
    private boolean limparDisplay = false;
    private TipoComando ultimaOperacao;

    private Memoria(){}

    public static Memoria getMemoria() {
        return memoria;
    }

    public void addObserver(MemoriaObserver obs) {
        observers.add(obs);
    }

    public void processarComando(String valor) {

        TipoComando tipo = pegarTipoComando(valor);

        if(tipo == null) {
            return;
        }

        switch (tipo) {
            case ZERAR ->
                    limpar();

            case NUMERO -> {
                    textDisplay = limparDisplay ? valor : textDisplay + valor;
                    limparDisplay = false;
            }

            case VIRGULA ->
                    textDisplay = textDisplay.contains(",") ? textDisplay : (textDisplay.isEmpty() ? "0" + valor: textDisplay + valor);

            case TROCAR_SINAL ->
                    trocarSinal();

            default -> {
                limparDisplay = true;

                if(tipo == TipoComando.PORCENTAGEM) {
                    validarPorcentagem();
                }

                efetuarOperacao();
                ultimaOperacao = tipo;
                buffer = textDisplay;
            }
        }

        observers.forEach(o -> o.valorAlterado(getTextDisplay()));
    }

    private void trocarSinal() {
        Double aux = Double.parseDouble(textDisplay.replace(",", "."));

        if(aux == 0.0) {
            return;
        }

        textDisplay = aux > 0 ?  "-".concat(textDisplay) : textDisplay.replace("-", "");
    }

    private void validarPorcentagem() {
        Double aux = Double.parseDouble(textDisplay.replace(",", "."));


        if(aux == 0.0 || this.buffer.length() < 1 || ultimaOperacao == TipoComando.PORCENTAGEM) {
            return;
        }

       Double buffer = Double.parseDouble(this.buffer.replace(",", "."));
       aux = ( buffer / 100)
               * aux;

       textDisplay = validarSeIntOuDouble(aux);
    }
    private void efetuarOperacao() {
        if(ultimaOperacao == null
            || ultimaOperacao == TipoComando.IGUAL
            || ultimaOperacao ==  TipoComando.PORCENTAGEM) {

            return;
        }
        double numBuffer = Double.parseDouble(buffer.replace(",", "."));
        double numAtual = Double.parseDouble(textDisplay.replace(",", "."));
        double resultado = 0;

        switch (ultimaOperacao) {
            case SOMA -> resultado = numBuffer + numAtual;
            case SUB -> resultado = numBuffer - numAtual;
            case MULT -> resultado = numBuffer * numAtual;
            case DIV -> resultado = numBuffer / numAtual;
        }

            textDisplay = validarSeIntOuDouble(resultado);
       }

    private TipoComando pegarTipoComando(String valor) {
        TipoComando retorno;

        if(textDisplay.isEmpty() && valor.equals("0")) {
            return null;
        }

        try {
            Integer.parseInt(valor);
            retorno = TipoComando.NUMERO;
        } catch (Exception e) {

            retorno = switch (valor.trim()) {

                case "AC" ->    TipoComando.ZERAR;
                case "/" ->     TipoComando.DIV;
                case "X" ->     TipoComando.MULT;
                case "+" ->     TipoComando.SOMA;
                case "-" ->     TipoComando.SUB;
                case "," ->     TipoComando.VIRGULA;
                case "%" ->     TipoComando.PORCENTAGEM;
                case "+/-" ->   TipoComando.TROCAR_SINAL;
                case "=" ->   TipoComando.IGUAL;
                default -> null;
            };
        }

        return retorno;
    }

    public String getTextDisplay() {
        return textDisplay.isEmpty() ? "0" : textDisplay;
    }

    private String validarSeIntOuDouble(double resultado) {
        boolean valorInt = String.valueOf(resultado).endsWith(".0");
        return valorInt ? String.valueOf((int) resultado) : String.valueOf(resultado).replace(".", ",");

    }

    private void limpar() {
        textDisplay = "";
        buffer = "";
        limparDisplay = false;
        ultimaOperacao = null;
    }
}
