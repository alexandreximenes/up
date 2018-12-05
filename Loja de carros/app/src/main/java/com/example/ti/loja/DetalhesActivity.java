package com.example.ti.loja;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.dao.ContatoDAO;

public class DetalhesActivity extends AppCompatActivity {

    private TextView txtID;
    private TextView txtNome;
    private TextView txtTipo;
    private TextView txtEmail;
    private TextView txtFone;
    private Button btnExcluir;
    private Button btnEditar;
    private Context applicationContext;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        txtID = findViewById(R.id.txtNomeContatoId);
        txtNome = findViewById(R.id.txtNomeContatoCad);
        txtTipo = findViewById(R.id.txtTipoContCad);
        txtEmail = findViewById(R.id.txtEmailContCad);
        txtFone = findViewById(R.id.txtFoneContCad);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnEditar = findViewById(R.id.btnEditar);


        Intent detalhesIntent = getIntent();
        int id = detalhesIntent.getIntExtra("id", -1);

        if (id == -1) {
            applicationContext = getApplicationContext();
            msg("Erro ao carregar detalhes do contato");
            startActivity(new Intent(applicationContext, MainActivity.class));

        } else {

            ContatoDAO dao = new ContatoDAO(getApplicationContext());
            contato = dao.get(id);

            txtID.setText("ID: #" + contato.getId());
            txtNome.setText("Nome: " + contato.getNome());
            txtTipo.setText("Tipo: " + contato.getTipo());
            txtEmail.setText("Email: " + contato.getEmail());
            txtFone.setText("Telefone: " + contato.getTelefone());


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent vaiParaCadastroActivity = new Intent(getApplicationContext(), CadastrarActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("id", contato.getId());
                    bundle.putString("nome", contato.getNome());
                    bundle.putString("tipo", contato.getTipo());
                    bundle.putString("email", contato.getEmail());
                    bundle.putString("telefone", contato.getTelefone());

                    vaiParaCadastroActivity.putExtras(bundle);
                    startActivity(vaiParaCadastroActivity);
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    applicationContext = getApplicationContext();

                    try{
                        ContatoDAO dao = new ContatoDAO(applicationContext);

                        if (dao.excluir(contato.getId())) {
                            msg("Contato excluida com sucesso!");
                        } else {
                            msg("Erro ao excluir contato");
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
