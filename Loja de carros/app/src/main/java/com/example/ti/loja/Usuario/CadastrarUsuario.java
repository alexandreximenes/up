package com.example.ti.loja.Usuario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.Contato;
import com.example.ti.loja.ListaUsuariosActivity;
import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.Util.FakeSenha;
import com.example.ti.loja.dao.UsuarioDAO;

public class CadastrarUsuario extends Activity {

    Button btLimpar, btSalvarUsuario, btVoltarParaLogin;
    Integer id;
    String foto, nome, email, senha;
    EditText edNome, edEmail, edSenha;
    ImageView imageFoto;
    TextView tvMsgUsuario;
    private boolean atualizar;
    private UsuarioDAO dao;
    private Usuario usuario;
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        applicationContext = getApplicationContext();

        edNome = findViewById(R.id.edNomeUsuario);
        edEmail = findViewById(R.id.edEmailUsuario);
        edSenha = findViewById(R.id.edSenhaUsuario);
        imageFoto = findViewById(R.id.imageUsuario);
        tvMsgUsuario = findViewById(R.id.tvMsgUsuario);


        btLimpar = findViewById(R.id.btLimparUsuario);
        btSalvarUsuario = findViewById(R.id.btSalvarUsuario);
        btVoltarParaLogin = findViewById(R.id.btVoltarUsuario);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        usuario = new Usuario();

        atualizar = false;

        if (bundle != null) {

            atualizar = true;

            id = bundle.getInt("id");
            foto = bundle.getString("foto");
            nome = bundle.getString("nome");
            email = bundle.getString("email");
            senha = bundle.getString("senha");

            //imagem imageFoto
            edNome.setText(nome);
            edEmail.setText(email);
            edSenha.setText(senha);

        }


        btSalvarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvMsgUsuario.setText("");
                //foto = imageFoto
                nome = edNome.getText().toString();
                email = edEmail.getText().toString();
                senha = edSenha.getText().toString();

                if (validacaoDeCampos()) return;

                setUsuario();

                dao = new UsuarioDAO(applicationContext);

                if (atualizar) {
                    atualizarUsuario(usuario);
                } else {
                    cadastrarUsuario(usuario);
                }


                startActivity(new Intent(applicationContext, ListaUsuariosActivity.class));

            }
        });
        btVoltarParaLogin.setOnClickListener(view -> finish());
    }

    private boolean validacaoDeCampos(){
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Mensagem.show(getApplicationContext(),"Preencha todos os campos", Toast.LENGTH_LONG);
            return true;
        }
        return false;
    }

    private void setUsuario() {

        usuario.setId(id);
        usuario.setFoto(foto);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

    }

    private void atualizarUsuario(Usuario usuario) {
        try {

            if (dao.atualizar(usuario)) {
                Mensagem.show(getApplicationContext(),"Contato atualizada com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Não foi possivel atualizar contato", Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            Log.d("Erro ao atualizar usuario", e.getMessage());
        }
    }

    private void cadastrarUsuario(Usuario usuario) {
        try {

            if (dao.cadastrar(usuario)) {
                Mensagem.show(getApplicationContext(),"Usuario atualizada com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Usuario atualizada com sucesso!", Toast.LENGTH_LONG);
            }

        } catch (Exception e) {
            Log.d("Erro ao atualizar usuario", e.getMessage());
        }
    }

}


