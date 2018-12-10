package com.example.ti.loja.Empresa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ti.loja.R;
import com.example.ti.loja.Usuario.CadastrarUsuario;
import com.example.ti.loja.Usuario.UsuarioAdapter;
import com.example.ti.loja.dao.EmpresaDAO;
import com.example.ti.loja.dao.UsuarioDAO;

import java.util.List;

public class ListaEmpresaActivity extends AppCompatActivity {

    private Button btnCadastrarEmpresa;
    private RecyclerView rclEmpresa;
    List<Empresa> empresas = null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresa);

        context = ListaEmpresaActivity.this;

        btnCadastrarEmpresa = findViewById(R.id.btnCadastrarEmpresa);
        rclEmpresa = findViewById(R.id.rclEmpresa);

        EmpresaDAO dao = new EmpresaDAO(context);

        empresas = dao.lista();

        EmpresaAdapter adapter = new EmpresaAdapter(empresas, context);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        rclEmpresa.setAdapter(adapter);
        rclEmpresa.setLayoutManager(layout);

        btnCadastrarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, CadastrarEmpresa.class));
            }
        });
    }
}
