package com.example.ti.loja;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ti.loja.Empresa.ListaEmpresaActivity;
import com.example.ti.loja.Produto.ListaProdutosActivity;
import com.example.ti.loja.Usuario.ListaUsuariosActivity;

public class MenuAdminActivity extends AppCompatActivity {

    Button btAdmin_usuarios, btAdmin_produtos, btAdmin_empresas;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        btAdmin_usuarios = findViewById(R.id.btAdmin_usuarios);
        btAdmin_produtos = findViewById(R.id.btAdmin_produtos);
        btAdmin_empresas = findViewById(R.id.btAdmin_empresas);

        context = getApplicationContext();

        btAdmin_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ListaUsuariosActivity.class));
            }
        });

        btAdmin_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ListaProdutosActivity.class));
            }
        });

        btAdmin_empresas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ListaEmpresaActivity.class));
            }
        });
    }
}
