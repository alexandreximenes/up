package br.edu.up.exemplolista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    EditText edtTitulo;
    EditText edtAno;
    EditText edtEstudio;
    Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtTitulo    = findViewById(R.id.edtTitulo);
        edtAno       = findViewById(R.id.edtAno);
        edtEstudio   = findViewById(R.id.edtEstudio);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtTitulo.getText().toString().isEmpty() ||
                   edtAno.getText().toString().isEmpty()    ||
                   edtEstudio.getText().toString().isEmpty() )
                {
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String titulo = edtTitulo.getText().toString();
                    int ano = Integer.parseInt(edtAno.getText().toString());
                    String estudio = edtEstudio.getText().toString();

                    Jogo j = new Jogo(titulo, ano, estudio);

                    JogoLista.addJogo(j);

                    Toast.makeText(CadastroActivity.this, "Jogo adicionado à lista", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(CadastroActivity.this, MainActivity.class));
                }

            }
        });


    }
}
