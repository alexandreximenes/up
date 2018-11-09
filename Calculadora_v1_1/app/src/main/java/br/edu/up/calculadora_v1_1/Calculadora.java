package br.edu.up.calculadora_v1_1;

import android.util.Log;

/**
 * Created by Sobreiro on 16/10/2018.
 */

public class Calculadora {

    private double valor;
    private double valorAnterior;
    private String operador;
    private String op;

    public Calculadora() {
        valor = 0;
        valorAnterior = 0;
        operador = "";
        op = "";
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private void realizarOperacao() {
        Log.i("OP: ",this.op);
        if (!operador.equals("")) {
            if (operador.equals("+")) {
                valor = valorAnterior + valor;
            } else if (operador.equals("-")) {
                valor = valorAnterior - valor;
            } else if (operador.equals("%")) {
                /**
                 * Calcula a porcentagem conforme operador anterior.
                 * Ex: 100 / 10% = 10 é o mesmo que 100/0,10
                 * Para obter esse resultado segue os passos
                 * Pressionar:
                 *      100
                 *      botao dividir
                 *      10
                 *      botao porcentagem
                 *      botao igual
                 */
                if(this.op.equals("+")){
                        valor = valorAnterior + (valorAnterior * valor) / 100;
                    }else if (this.op.equals("-")){
                        valor = valorAnterior - ((valorAnterior * valor) / 100);
                    }else if(this.op.equals("/")){
                        valor = (valorAnterior / (valor / 100));
                    }else{
                        valor = valor * (valorAnterior / 100);
                    }

            } else if (operador.equals("*")) {
                valor = valorAnterior * valor;
            } else if (operador.equals("/")) {
                if (valor != 0) {
                    valor = valorAnterior / valor;
                }
            }
        }
    }

    public void verificarOperacao(String op) {

        if (op.equals("LIMPAR")) {
            valor = 0;
            operador = "";
        } else {
            if(op.equals("%")) {
                this.op = operador;
                operador = "";
            }
        }

        realizarOperacao();
        System.out.println(valor);
        operador = op;
        if(op.equals("%")){
            return;
        }
        valorAnterior = valor;

    }
}
