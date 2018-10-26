package com.example.up.calculadora_v1;

public class Calculadora {

    private double numero;
    private double numeroAnterior;
    private String op;

    public Calculadora() {
        this.numeroAnterior = 0;
        this.numero = 0;
        this.op = "";
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public void verificarOperacao(String op) {
        if ( op.equals("LIMPAR") ) {
            numero = 0;
            this.op = "";
        } else {
            realizarOperacao();
            this.op = op;
            numeroAnterior = numero;
        }
    }

    private void realizarOperacao() {
        if (!op.equals("")) {
            switch (op) {
                case "+":
                    numero += numeroAnterior;
                    break;

                case "-":
                    numero -= numeroAnterior;
                    break;

                case "*":
                    numero *= numeroAnterior;
                    break;

                case "/":
                    if (numero != 0) {
                        numero /= numeroAnterior;
                    }
                    break;
            }
        }
    }


}
