package alexandre.com.br.folhadepagamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PagamentoActivity extends AppCompatActivity {

    TextView tvNome, tvHorasTrab, tvValorHora, tvSalBruto, tvSalLiq, tvIR, tvINSS, tvFGTS;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        tvNome = findViewById(R.id.tvNomeFuncionario);
        tvHorasTrab = findViewById(R.id.tvHoraTrabalhada);
        tvValorHora = findViewById(R.id.tvValorHoraTrab);
        tvSalBruto = findViewById(R.id.tvSalarioBruto);
        tvSalLiq = findViewById(R.id.tvSalarioLiquido);
        tvIR = findViewById(R.id.tvIR);
        tvINSS = findViewById(R.id.tvINSS);
        tvFGTS = findViewById(R.id.tvFGTS);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String nome = bundle.getString("nome");
        int horasTrab = bundle.getInt("tvHorasTrab");
        float valorHora = bundle.getFloat("tvValorHora");

        Folha folha = new Folha(nome, 6, 100);

        tvNome.setText(nome);
        tvHorasTrab.setText(String.valueOf(horasTrab));
        tvValorHora.setText(String.valueOf(valorHora));
        tvSalBruto.setText(String.valueOf(folha.getSalBruto()));
        tvSalLiq.setText(String.valueOf(folha.getSalLiq()));
        tvIR.setText(String.valueOf(folha.getIr()));
        tvINSS.setText(String.valueOf(folha.getInss()));
        tvFGTS.setText(String.valueOf(folha.getFgts()));


        btVoltar = findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
