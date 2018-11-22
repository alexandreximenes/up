package br.edu.up.exemplolista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtTituloCad;
    TextView txtAnoCad;
    TextView txtEstudioCad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtTituloCad  = findViewById(R.id.txtTituloCad);
        txtAnoCad     = findViewById(R.id.txtAnoCad);
        txtEstudioCad = findViewById(R.id.txtEstudioCad);

        Intent intentDetalhes = getIntent();
        int index = intentDetalhes.getIntExtra("index", -1);

        if(index != -1)
        {
            Jogo j = JogoLista.getJogo(index);

            txtTituloCad.setText("TÍTULO: " + j.getTitulo());
            txtAnoCad.setText("ANO: " + String.valueOf(j.getAno()));
            txtEstudioCad.setText("ESTÚDIO: " + j.getEstudio());
        }else{

            Toast.makeText(this, "Não foi possível carregar os dados do jogo", Toast.LENGTH_SHORT).show();
        }

    }
}
