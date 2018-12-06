package com.example.ti.loja.Usuario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.ListaUsuariosActivity;
import com.example.ti.loja.MainActivity;
import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.dao.UsuarioDAO;

public class CadastrarUsuario extends Activity {

    Button btLimpar, btSalvarUsuario, btVoltarParaLogin;
    String foto, nome, email, senha;
    EditText edNome, edEmail, edSenha;
    ImageView imageFoto;
    TextView tvMsgUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Context applicationContext = getApplicationContext();

        edNome = findViewById(R.id.edNomeUsuario);
        edEmail = findViewById(R.id.edEmailUsuario);
        edSenha = findViewById(R.id.edSenhaUsuario);
        imageFoto = findViewById(R.id.imageUsuario);
        tvMsgUsuario = findViewById(R.id.tvMsgUsuario);

        btLimpar = findViewById(R.id.btLimparUsuario);
        btSalvarUsuario = findViewById(R.id.btSalvarUsuario);
        btVoltarParaLogin = findViewById(R.id.btVoltarUsuario);

        btSalvarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = edNome.getText().toString();
                email = edEmail.getText().toString();
                senha = edSenha.getText().toString();
                //foto = imageFoto

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    tvMsgUsuario.setText("Preencha todos os campos");
                    Mensagem.show(applicationContext, "Preencha seu nome, email e senha", Toast.LENGTH_SHORT);
                }else{
                    tvMsgUsuario.setText("");
                    UsuarioDAO dao = new UsuarioDAO(applicationContext);
                    dao.cadastrar(new Usuario(foto, nome, email, senha));
                    Mensagem.show(applicationContext, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT);
                    startActivity(new Intent(applicationContext, ListaUsuariosActivity.class));
                }
            }
        });
        btVoltarParaLogin.setOnClickListener(view -> finish());
    }

}
