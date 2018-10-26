package br.edu.up.calculadora_v1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Calculadora calc;
    TextView txtVisor;

    boolean digitando;
    boolean separadorDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVisor = findViewById(R.id.txtVisor);
        digitando = false;
        separadorDecimal = false;
        calc = new Calculadora();

        txtVisor.setText("0");

    }

    public void onClickValores(View view){

        Button btnPressionado = (Button)view;
        String valorBotao = btnPressionado.getText().toString();
        String textoDoVisor = txtVisor.getText().toString();

        if(!digitando || textoDoVisor.equals("0")){

            txtVisor.setText(valorBotao);

            if(!valorBotao.equals("0")){
                digitando = true;
            }

        }else{
            txtVisor.setText(textoDoVisor + valorBotao);
        }

    }

    public void onClickOperacoes(View view){

        Button btnOperacao = (Button)view;
        String op = btnOperacao.getText().toString();
        String textoDoVisor = txtVisor.getText().toString();

        if(op.equals(",") && !separadorDecimal){
            separadorDecimal = true;

            if(!digitando){
                txtVisor.setText("0,");
            }else{
                txtVisor.setText(textoDoVisor + ",");
            }

            digitando = true;

        }else if (!op.equals(",")){


            if(op.equals("LIMPAR")){

                txtVisor.setText("0");
                digitando = false;
                separadorDecimal = false;
                calc.verificarOperacao(op);

            }else {

                String valorConvertido = txtVisor.getText().toString().replace(',', '.');

                calc.setValor(Float.parseFloat(valorConvertido));
                calc.verificarOperacao(op);

                String textoResultado = String.valueOf(calc.getValor());

                if (textoResultado.endsWith(".0")) {
                    textoResultado = textoResultado.substring(0, textoResultado.length() - 2);
                }

                txtVisor.setText(textoResultado.replace('.', ','));
                digitando = false;
                separadorDecimal = false;
            }
        }



    }

}
