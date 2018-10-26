package br.edu.up.calculadora_v1_1;

/**
 * Created by Sobreiro on 16/10/2018.
 */

public class Calculadora {

    private float valor;
    private float valorAnterior;
    private String operador;

    public Calculadora(){
        valor = 0;
        valorAnterior = 0;
        operador = "";
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    private void realizarOperacao(){

        if(!operador.equals("")){
            if(operador.equals("+")){
                valor = valorAnterior + valor;
            }else if(operador.equals("-")){
                valor = valorAnterior - valor;
            }else if(operador.equals("*")){
                valor = valorAnterior * valor;
            }else if(operador.equals("%")){
                valor = valor * valorAnterior/100;
            }else if(operador.equals("/")){
                if(valor != 0){
                    valor = valorAnterior / valor;
                }
            }
        }
    }

    public void verificarOperacao(String op){

        if(op.equals("LIMPAR")){
            valor = 0;
            operador = "";
        }else{
            realizarOperacao();
            operador = op;
            valorAnterior = valor;
        }

    }

}
