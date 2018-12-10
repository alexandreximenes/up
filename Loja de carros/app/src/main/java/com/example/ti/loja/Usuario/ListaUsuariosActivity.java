package com.example.ti.loja.Usuario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ti.loja.R;
import com.example.ti.loja.dao.UsuarioDAO;

import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity {

    private Button btnCadastrarUsuario;
    private RecyclerView rclUsuarios;
    List<Usuario> usuarios = null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        context = ListaUsuariosActivity.this;

        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);
        rclUsuarios = findViewById(R.id.rclUsuarios);

        UsuarioDAO dao = new UsuarioDAO(context);

        usuarios = dao.lista();

        UsuarioAdapter adapter = new UsuarioAdapter(usuarios, context);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        rclUsuarios.setAdapter(adapter);
        rclUsuarios.setLayoutManager(layout);

        btnCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, CadastrarUsuario.class));
            }
        });
    }
}
