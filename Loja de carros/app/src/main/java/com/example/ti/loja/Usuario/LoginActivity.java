package com.example.ti.loja.Usuario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.MenuAdminActivity;
import com.example.ti.loja.R;
import com.example.ti.loja.dao.UsuarioDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button novoUsuario, entrarSistema;
    EditText edNome, edSenha;
    TextView tvMsg;
    String nome, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullScreen();
        setContentView(R.layout.activity_login);
        Context applicationContext = getApplicationContext();

        edNome = findViewById(R.id.edEmailLogin);
        edSenha = findViewById(R.id.edSenha);
        tvMsg = findViewById(R.id.tvMsg);

        novoUsuario = findViewById(R.id.btNovoUsuario);
        entrarSistema = findViewById(R.id.btEntrarSistema);

        novoUsuario.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, CadastrarUsuario.class)));

        entrarSistema.setOnClickListener(view -> {
            nome = edNome.getText().toString();
            senha = edSenha.getText().toString();
            if(nome.isEmpty() || senha.isEmpty()) {
                tvMsg.setText("Usuário ou senha inválido");
                Mensagem.show(applicationContext, "Preencha os campos corretamente para entrar no sistema", Toast.LENGTH_LONG);
            }else{
                tvMsg.setText("");
                UsuarioDAO dao = new UsuarioDAO(applicationContext);
                Usuario usuario = dao.getNomeESenha(nome, senha);
                if(usuario !=null) {
                    List<Usuario> lista = dao.lista();
//                    Mensagem.show(applicationContext, lista.toString(), Toast.LENGTH_LONG);
                    startActivity(new Intent(applicationContext, MenuAdminActivity.class));
                }else {
                    Mensagem.show(applicationContext, "Usuário não encontrado no banco de dados", Toast.LENGTH_LONG);
                    return;
                }
            }
        });
    }




    private void fullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
