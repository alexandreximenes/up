package com.example.up.gasolina_ou_alcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txtValor;
    TextView txtResposta;
    Button btnCalcular;
    SeekBar skbValor;

    float valor;
    String valorFormatado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referenciar os elementos da interface
        txtValor    = findViewById(R.id.txtValor);
        txtResposta = findViewById(R.id.txtResposta);
        btnCalcular = findViewById(R.id.btnCalcular);
        skbValor    = findViewById(R.id.skbValor);


        // capturar o valor da seek bar
        skbValor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                valor = (float)progress;
                valorFormatado = String.format(Locale.FRANCE, "%.2f", valor/100.0);

                txtValor.setText("R$ " + valorFormatado);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor = (valor/100.0f) * 0.7f;

                valorFormatado = String.format(Locale.FRANCE, "%.2f", valor);

                txtResposta.setText("Abasteça com Àlcool se ele custar até R$ " + valorFormatado);

            }
        });


    }
}
