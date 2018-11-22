package com.alexandre.up.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalheContato extends AppCompatActivity {

    private TextView tvNome, tvIdade, tvEmail;
    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_contato);

        lerViews();
        apresentarDados(getIntent().getExtras() );
        //Bundle extras = getIntent().getExtras();
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela();
            }
        });
    }

    private void voltarTela() {
        finish();
    }

    private void apresentarDados(Bundle extras) {
        tvNome.setText( tvNome.getText().toString() + extras.get("nome").toString()                   );
        tvIdade.setText( tvIdade.getText().toString() + String.valueOf( extras.getInt("idade"))  );
        tvEmail.setText( tvEmail.getText().toString() + extras.get("email").toString()                );
    }

    private void lerViews() {
        tvNome = findViewById(R.id.tvNome);
        tvIdade = findViewById(R.id.tvIdade);
        tvEmail = findViewById(R.id.tvEmail);
        btVoltar = findViewById(R.id.btVoltar);
    }
}
