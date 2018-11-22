package com.example.ti.cadastrobd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.cadastrobd.dao.FilmeDAO;

public class DetalhesActivity extends AppCompatActivity {

    private TextView txtID;
    private TextView txtNome;
    private TextView txtAno;
    private TextView txtMidia;
    private Button btnExcluir;
    private Button btnAlterar;
    private Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        txtID = findViewById(R.id.txtID);
        txtNome = findViewById(R.id.txtNome);
        txtAno = findViewById(R.id.txtAno);
        txtMidia = findViewById(R.id.txtMidia);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnAlterar = findViewById(R.id.btnAlterar);


        Intent detalhesIntent = getIntent();
        int index = detalhesIntent.getIntExtra("index", -1);

        if (index == -1) {
            applicationContext = getApplicationContext();
            msg("Erro ao carregar detalhes do filme");
            startActivity(new Intent(applicationContext, MainActivity.class));

        } else {

            final Filme filme = ListaFilmes.get().get(index);
            txtID.setText("ID: #" + filme.getId());
            txtNome.setText("Nome: " + filme.getNome());
            txtAno.setText("Nome: " + filme.getAno());
            txtMidia.setText("Nome: " + filme.getMidia());


            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent updateIntent = new Intent(getApplicationContext(), CadastrarActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("id", filme.getId());
                    bundle.putString("nome", filme.getNome());
                    bundle.putString("ano", filme.getAno());
                    bundle.putString("midia", filme.getMidia());

                    updateIntent.putExtras(bundle);
                    startActivity(updateIntent);
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    applicationContext = getApplicationContext();

                    try{
                        FilmeDAO dao = new FilmeDAO(applicationContext);

                        if (dao.excluir(filme.getId())) {
                            msg("Filme excluida com sucesso!");
                        } else {
                            msg("Erro ao excluir filme");
                        }
                        startActivity(new Intent(applicationContext, MainActivity.class));

                    }catch (Exception e){
                        msg(e.getMessage().toString());
                    }
                }
            });


        }


    }

    private void msg(String s) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show();
    }
}
