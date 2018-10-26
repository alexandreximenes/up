package com.example.up.calculadora_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculadora calc = null;
    private boolean digitando;

    TextView txtVisor;
    Button
            btnVirgula,         //Botao ,
            btnIgual,           //Botao =
            btnMultiplicacao,   //Botao *
            btnSoma,            //Botao +
            btnDivisao,         //Botao /
            btnSubtracao,       //Botao -
            btnLimpar,          //Botao Limpar
            btn0, //Botao 0
            btn1, //Botao 1
            btn2, //Botao 2
            btn3, //Botao 3
            btn4, //Botao 4
            btn5, //Botao 5
            btn6, //Botao 6
            btn7, //Botao 7
            btn8, //Botao 8
            btn9; //Botao 9

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciandoCalc();

    }

    private void iniciandoCalc() {
        txtVisor = (TextView) findViewById(R.id.txtVisor);
        calc = new Calculadora();
        digitando = false;
        txtVisor.setText("0");
    }

    public void onClickNumeros(View view){
        Button btnNumero = (Button) view;
        String textoVisor = txtVisor.getText().toString();

        if(!textoVisor.equals("0")){
            textoVisor += btnNumero.getText().toString();
            txtVisor.setText(textoVisor);
        }else{
            if(btnNumero.getText().toString().equals("0")){
                txtVisor.setText("0");
            }else{
                textoVisor = btnNumero.getText().toString();
                txtVisor.setText(textoVisor);
            }
        }

        double numero = Double.parseDouble(textoVisor);
        calc.setNumero(numero);
    }

    public void onClickOperacoes(View view){
        Button btnOp = (Button) view;
        Double numeroVisor = Double.parseDouble(txtVisor.getText().toString());

        calc.setNumero(numeroVisor);
        calc.verificarOperacao(btnOp.getText().toString());
    }
}
