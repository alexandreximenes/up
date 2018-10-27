package br.edu.up.calculadora_v1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    Calculadora calc;
    TextView txtVisor;

    boolean digitando;
    boolean separadorDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

//                String valorConvertido = txtVisor.getText().toString().replace(',', '.').replace(".", "");
                String valorConvertido = null;
                NumberFormat nFormat = NumberFormat.getNumberInstance();
                try {
                    valorConvertido = String.valueOf(nFormat.parse(txtVisor.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                calc.setValor(Float.parseFloat(valorConvertido));
                calc.verificarOperacao(op);

                String textoResultado = String.valueOf(calc.getValor());

                if (textoResultado.endsWith(".0")) {
                    textoResultado = textoResultado.substring(0, textoResultado.length() - 2);
                }

                txtVisor.setText(textoResultado.replace('.', ','));
                if(op.equals("=")) txtVisor.setText(nFormat.format(Double.parseDouble(textoResultado)));
                digitando = false;
                separadorDecimal = false;
            }
        }



    }

}
