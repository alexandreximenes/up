package com.example.ti.cadastrobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ti.cadastrobd.dao.ContatoDAO;

import java.util.List;

/**
 * Crie um novo projeto no Android Studio com o nome Atividade Filmes
 *
 *
 * Crie um novo arquivo de layout na pasta 'layouts' (layouts > [clicar com o botão direito] > new > layout resource file)
 * Um dos text views ira exibir o nome do filme, enquanto o outro irá exibir o texto 'ver mais'
 * Na main activity deve conter um botão para cadastrar novo filme. este botão deverá abrir a activity criada anteriormente.Ainda na main activity, crie uma recycler view para exibir os filmes cadastrados.
 * Crie uma classe  ContatoAdapter e uma classe ContatoHolder para poder utilizar a recycler view cadastrada.
 * Crie a Activity Detalhes para exibir todos os dados do filme
 */
public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private RecyclerView rclFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        rclFilmes = findViewById(R.id.rclFilmes);


        ContatoDAO dao = new ContatoDAO(MainActivity.this);

        List<Contato> contatoes = dao.lista();

        ContatoAdapter adapter = new ContatoAdapter(contatoes, MainActivity.this);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        rclFilmes.setAdapter(adapter);
        rclFilmes.setLayoutManager(layout);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastrarActivity.class));
            }
        });




    }
}
