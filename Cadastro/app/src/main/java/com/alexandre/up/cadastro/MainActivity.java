package com.alexandre.up.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edNome, edIdade, edEmail;
    private Button btSalvar;
    Contato contato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.edNome);
        edIdade = findViewById(R.id.edIdade);
        edEmail = findViewById(R.id.edEmail);

        btSalvar = findViewById(R.id.btCadastrar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edNome.getText().toString().isEmpty() || edIdade.getText().toString().isEmpty() || edEmail.getText().toString().isEmpty()) {
                    msg("Os campos não podem estar em branco.");
                    return;
                } else {
                    contato = new Contato()
                        .setNome(edNome.getText().toString())
                        .setIdade(edIdade.getText().toString())
                        .setEmail(edEmail.getText().toString());
                    contato.salva(contato);

                    msg("Salvo com sucesso!");
                    limparCampos();
                    enviarParaActivityDetalheContato(contato.getNome(), contato.getIdade(), contato.getEmail());
                }
            }
        });
    }

    private void limparCampos() {
        edNome.setText("");
        edIdade.setText("");
        edEmail.setText("");
        edNome.requestFocus();
    }

    private void enviarParaActivityDetalheContato(String nome, int idade, String email) {
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        bundle.putInt("idade", idade);
        bundle.putString("email", email);

        Intent intent = new Intent(MainActivity.this, DetalheContato.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void msg(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

}
