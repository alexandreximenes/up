package com.example.ti.cadastrobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.cadastrobd.Util.Midia;
import com.example.ti.cadastrobd.dao.FilmeDAO;


public class CadastrarActivity extends AppCompatActivity {


    private EditText edtNome;
    private EditText edtAno;
    private EditText edtMidia;
    private Button btnSalvar;
    private TextView txtTitulo2;


    private int id;
    private String nome;
    private String ano;
    private String midia;
    private boolean atualizar;
    private Filme filme;
    FilmeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edtNome = findViewById(R.id.edtNome);
        edtAno = findViewById((R.id.edtAno));
        edtMidia = findViewById(R.id.edtMidia);

        btnSalvar = findViewById(R.id.btnSalvar);
        txtTitulo2 = findViewById(R.id.txtTitulo2);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        atualizar = false;

        if (bundle != null) {

            txtTitulo2.setText("EDITAR FILME");

            atualizar = true;

            id = bundle.getInt("id");
            nome = bundle.getString("nome");
            ano = bundle.getString("ano");
            midia = bundle.getString("midia");

            edtNome.setText(nome);
            edtAno.setText(ano);
            edtMidia.setText(midia);

        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nome = edtNome.getText().toString();
                ano = edtAno.getText().toString();
                midia = edtMidia.getText().toString().toUpperCase();

                if (validacaoDeCampos()) return;

                setFilme();
                dao = new FilmeDAO(CadastrarActivity.this);

                if (atualizar) {
                    atualizarFilme(filme);
                } else {
                    cadastrarFilme(filme);
                }

                startActivity(new Intent(getApplicationContext(), MainActivity.class));


            }
        });

    }

    private boolean validacaoDeCampos() {
        if (nome.isEmpty() || ano.isEmpty() || midia.isEmpty()) {
            msg("Preencha todos os campos");
            return true;
        }
        if (!(midia.equals(Midia.BLU_RAY.getMidia()) || midia.equals(Midia.DIGITAL.getMidia()) || midia.equals(Midia.DVD.getMidia())) ) {
            msg("Preencha o campo corretamente, valores aceitaveil [DVD, DIGITAL, BLU-RAY]");
            return true;

        }
        return false;
    }

    private void setFilme() {
        filme = new Filme();

        switch (midia) {
            case "DVD":
                filme.setMidia(Midia.DVD.getMidia());
                break;
            case "DIGITAL":
                filme.setMidia(Midia.DIGITAL.getMidia());
                break;
            case "BLURAY":
            case "BLU-RAY":
                filme.setMidia(Midia.BLU_RAY.getMidia());
                break;
        }

        filme.setId(id);
        filme.setNome(nome);
        filme.setAno(ano);
    }

    private void atualizarFilme(Filme filme) {
        try {

            if (dao.atualizar(filme)) {
                msg("Filme atualizada com sucesso!");
            } else {
                msg("Não foi possivel atualizar filme");
            }
        } catch (Exception e) {
            msg(e.getMessage());
        }
    }

    private void cadastrarFilme(Filme filme) {
        try {

            if (dao.cadastrar(filme)) {
                msg("Filme cadastrada com sucesso!");
            } else {
                msg("Não foi possivel salvar filme");
            }

        } catch (Exception e) {
            msg("Erro ao conectar ...");
        }
    }

    private void msg(String msg) {
        Toast.makeText(CadastrarActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
