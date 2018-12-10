package com.example.ti.loja.Empresa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.dao.EmpresaDAO;

public class DetalhesEmpresaActivity extends Activity {

    private TextView txtID, txtNomeEmpresa, txtEmailEmpresa, txtSiteEmpresa, txtTelefoneEmpresa, txtEnderecoEmpresa;
    private ImageView fotoEmpresa;
    private Button btnExcluir, btnEditar;
    private Context applicationContext;
    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_empresa);

        applicationContext = getApplicationContext();

        txtID = findViewById(R.id.txtIdEmpresaDetalhe);
        txtNomeEmpresa = findViewById(R.id.txtNomeEmpresaDetalhe);
        txtEmailEmpresa = findViewById(R.id.txtEMailEmpresaDetalhe);
        txtSiteEmpresa = findViewById(R.id.txtSiteEmpresaDetalhe);
        txtTelefoneEmpresa = findViewById(R.id.txtTelefoneEmpresaDetalhe);
        txtEnderecoEmpresa = findViewById(R.id.txtEnderecoEmpresaDetalhe);
        fotoEmpresa = findViewById(R.id.imageimageDetalheEmpresa);
        btnExcluir = findViewById(R.id.btnExcluirEmpresaDetalhe);
        btnEditar = findViewById(R.id.btnEditarEmpresaDetalhe);


        Intent detalhesIntent = getIntent();
        int id = detalhesIntent.getIntExtra("id", -1);

        if (id == -1) {
            Mensagem.show(getApplicationContext(), "Erro ao carregar detalhes do empresa", Toast.LENGTH_LONG);
            startActivity(new Intent(applicationContext, ListaEmpresaActivity.class));

        } else {

            EmpresaDAO dao = new EmpresaDAO(applicationContext);
            empresa = dao.get(id);

            txtID.setText("ID: #" + empresa.getId());
//            fotoUsuario...
            txtNomeEmpresa.setText("NOME: " + empresa.getNome());
            txtEmailEmpresa.setText("EMAIL: " + empresa.getEmail());
            txtSiteEmpresa.setText("SITE: " + empresa.getSite());
            txtEnderecoEmpresa.setText("ENDEREÇO: " + empresa.getEndereco());
            txtTelefoneEmpresa.setText("TELEFONE: " + empresa.getTelefone());


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent vaiParaCadastroActivity = new Intent(getApplicationContext(), CadastrarEmpresa.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("id", empresa.getId());
                    bundle.putString("foto", empresa.getFoto());
                    bundle.putString("nome", empresa.getNome());
                    bundle.putString("email", empresa.getEmail());
                    bundle.putString("endereco", empresa.getEndereco());
                    bundle.putString("site", empresa.getSite());
                    bundle.putString("telefone", empresa.getTelefone());

                    vaiParaCadastroActivity.putExtras(bundle);
                    startActivity(vaiParaCadastroActivity);
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        EmpresaDAO dao = new EmpresaDAO(applicationContext);

                        if (dao.excluir(empresa.getId())) {
                            Mensagem.show(applicationContext, "Empresa excluida com sucesso!", Toast.LENGTH_LONG);
                        } else {
                            Mensagem.show(applicationContext, "Erro ao excluir empresa", Toast.LENGTH_LONG);
                        }
                        startActivity(new Intent(applicationContext, ListaEmpresaActivity.class));

                    } catch (Exception e) {
                        Mensagem.show(applicationContext, e.getMessage().toString(), Toast.LENGTH_LONG);
                    }
                }
            });


        }

    }
}
