package br.edu.up.exemplolista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lstJogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        lstJogos = findViewById(R.id.lstJogos);

        /*
        Abaixo iremos criar um adaptador para informar a lstJogos qual o
        tipo de dado ela deverá exibir (um Jogo), qual a fonte de dados
        (listaJogos) e qual o formato do layout (padrão vertical)
        */

        ArrayAdapter<Jogo> adaptadorJogos = new ArrayAdapter<Jogo>(
               MainActivity.this,
                android.R.layout.simple_list_item_1,
                JogoLista.getLista());

        lstJogos.setAdapter(adaptadorJogos);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });

        lstJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent detalhesIntent = new Intent(MainActivity.this, DetalhesActivity.class);
               detalhesIntent.putExtra("index", i);
               startActivity(detalhesIntent);

            }
        });

    }
}
