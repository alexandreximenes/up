package com.example.ti.cadastrobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.cadastrobd.Util.Tipo;
import com.example.ti.cadastrobd.dao.ContatoDAO;


public class CadastrarActivity extends AppCompatActivity {


    private EditText edtNome;
    private RadioGroup rdTipo;
    private RadioButton rdTipoEscolhido;
    private RadioButton rdPessoal, rdComercial;
    private EditText edtEmail;
    private EditText edtTelefone;
    private Button btnSalvar;
    private TextView txtTitulo;


    private int id;
    private String nome;
    private String email;
    private String tipo;
    private String telefone;
    private boolean atualizar;
    private Contato contato;
    ContatoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edtNome = findViewById(R.id.edtNome);
        edtTelefone = findViewById(R.id.edtFone);
        edtEmail = findViewById(R.id.edtEmail);
        rdTipo = findViewById((R.id.rdgPrioridade));
        rdTipoEscolhido = findViewById(rdTipo.getCheckedRadioButtonId());
        btnSalvar = findViewById(R.id.btnSalvar);
        txtTitulo = findViewById(R.id.txtTitulo);
        rdPessoal = findViewById(R.id.rdbPessoal);
        rdComercial = findViewById(R.id.rdbComercial);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        contato = new Contato();

        atualizar = false;

        if (bundle != null) {

            txtTitulo.setText("EDITAR CADASTRO");

            atualizar = true;

            id = bundle.getInt("id");
            nome = bundle.getString("nome");
            tipo = bundle.getString("tipo");
            email = bundle.getString("email");
            telefone = bundle.getString("telefone");

            edtNome.setText(nome);

            if(tipo.equals(Tipo.PESSOAL.getTipo())){
                rdPessoal.setChecked(true);
            }else{
                rdComercial.setChecked(true);
            }

            edtTelefone.setText(telefone);
            edtEmail.setText(email);

        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nome = edtNome.getText().toString();
                email = edtEmail.getText().toString();
                telefone = edtTelefone.getText().toString();

                if (validacaoDeCampos()) return;

                setContato();
                dao = new ContatoDAO(CadastrarActivity.this);

                if (atualizar) {
                    atualizarContato(contato);
                } else {
                    cadastrarContato(contato);
                }

                startActivity(new Intent(getApplicationContext(), MainActivity.class));


            }
        });

    }

    private boolean validacaoDeCampos() {
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()|| tipo.isEmpty()) {
            msg("Preencha todos os campos");
            return true;
        }
        if (!(tipo.equals(Tipo.COMERCIAL.getTipo()) || tipo.equals(Tipo.PESSOAL.getTipo())) ) {
            msg("Escolha uma opção de contato [PESSOAL ou COMERCIAL]");
            return true;

        }
        return false;
    }

    private void setContato() {

        contato.setId(id);
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
    }

    private void atualizarContato(Contato contato) {
        try {

            if (dao.atualizar(contato)) {
                msg("Contato atualizada com sucesso!");
            } else {
                msg("Não foi possivel atualizar contato");
            }
        } catch (Exception e) {
            msg(e.getMessage());
        }
    }

    private void cadastrarContato(Contato contato) {
        try {

            if (dao.cadastrar(contato)) {
                msg("Contato cadastrada com sucesso!");
            } else {
                msg("Não foi possivel salvar contato");
            }

        } catch (Exception e) {
            msg("Erro ao conectar ...");
        }
    }

    private void msg(String msg) {
        Toast.makeText(CadastrarActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rdbPessoal:
                if (checked)
                    contato.setTipo(Tipo.PESSOAL.getTipo());
                tipo = Tipo.PESSOAL.getTipo();
                break;
            case R.id.rdbComercial:
                if (checked)
                    contato.setTipo(Tipo.COMERCIAL.getTipo());
                    tipo = Tipo.COMERCIAL.getTipo();
                    break;
        }
    }
}
