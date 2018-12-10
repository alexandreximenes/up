package com.example.ti.loja.Empresa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.dao.EmpresaDAO;

import java.io.File;

public class CadastrarEmpresa extends Activity {

    Button btLimpar, btSalvarEmpresa, btVoltarParaLogin, btTirarFoto;
    Integer id;
    String foto, nome, site, telefone, email, endereco;
    EditText edNome, edSite, edTelefone, edEmail, edEndereco;
    ImageView imageFoto;
    TextView tvMsgEmpresa;
    private boolean atualizar;
    private EmpresaDAO dao;
    private Empresa empresa;
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);

        applicationContext = getApplicationContext();

        edNome = findViewById(R.id.edNomeLoja);
        edSite = findViewById(R.id.edSiteLoja);
        edTelefone = findViewById(R.id.edTelefoneLoja);
        edEmail = findViewById(R.id.edEmailLoja);
        edEndereco = findViewById(R.id.edEnderecoLoja);
        imageFoto = findViewById(R.id.imageLoja);
        tvMsgEmpresa = findViewById(R.id.tvMsgLoja);


        btLimpar = findViewById(R.id.btLimparLoja);
        btTirarFoto = findViewById(R.id.btnTirarFotoEmpresa);
        btSalvarEmpresa = findViewById(R.id.btSalvarLoja);
        btVoltarParaLogin = findViewById(R.id.btVoltarLoja);

        btTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CadastrarEmpresa.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String caminhoFoto = getExternalFilesDir(null)+"/loja"+ System.currentTimeMillis() +".jpg";
                Log.d("caminho foto empresa", caminhoFoto);
                File imagePath = new File(getApplicationContext().getFilesDir(), "images");
                File newFile = new File(imagePath, caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
                startActivityForResult(intentCamera, 1);
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        empresa = new com.example.ti.loja.Empresa.Empresa();
        atualizar = false;

        if (bundle != null) {
            atualizar = true;
            id = bundle.getInt("id");
            foto = bundle.getString("foto");
            nome = bundle.getString("nome");
            email = bundle.getString("email");
            site = bundle.getString("site");
            telefone = bundle.getString("telefone");
            endereco = bundle.getString("endereco");

            //imagem imageFoto
            edNome.setText(nome);
            edEmail.setText(email);
            edSite.setText(site);
            edTelefone.setText(telefone);
            edEndereco.setText(endereco);

        }

        btSalvarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //foto = imageFoto
                nome = edNome.getText().toString();
                email = edEmail.getText().toString();
                site = edSite.getText().toString();
                telefone = edTelefone.getText().toString();
                endereco = edEndereco.getText().toString();

                if (validacaoDeCampos()) return;
                tvMsgEmpresa.setText("");
                setEmpresa();

                dao = new EmpresaDAO(applicationContext);

                if (atualizar) {
                    atualizarEmpresa(empresa);
                } else {
                    cadastrarEmpresa(empresa);
                }


                startActivity(new Intent(applicationContext, ListaEmpresaActivity.class));

            }
        });
        btVoltarParaLogin.setOnClickListener(view -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imageFoto.setImageBitmap(bitmap);

        }
    }

    private boolean validacaoDeCampos(){
        if (nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
            Mensagem.show(getApplicationContext(),"Preencha os campos obrigatórios", Toast.LENGTH_LONG);
            tvMsgEmpresa.setText("Nome, telefone e endereço são obrigatórios");
            if(endereco.isEmpty()){
                edEndereco.requestFocus();
            }
            if(telefone.isEmpty()){
                edTelefone.requestFocus();
            }
            if(nome.isEmpty()){
                edNome.requestFocus();
            }

            return true;
        }
        return false;
    }

    private void setEmpresa() {

        empresa.setId(id);
        empresa.setFoto(foto);
        empresa.setNome(nome);
        empresa.setEmail(email);
        empresa.setEndereco(endereco);
        empresa.setTelefone(telefone);
        empresa.setSite(site);

    }

    private void atualizarEmpresa(Empresa empresa) {
        try {

            if (dao.atualizar(empresa)) {
                Mensagem.show(getApplicationContext(),"Empresa atualizada com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Não foi possivel atualizar empresa", Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            Log.d("Erro ao atualizar empresa", e.getMessage());
        }
    }

    private void cadastrarEmpresa(Empresa empresa) {
        try {

            if (dao.cadastrar(empresa)) {
                Mensagem.show(getApplicationContext(),"Empresa(a) cadastrado(a) com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Empresa(a) atualizado(a) com sucesso!", Toast.LENGTH_LONG);
            }

        } catch (Exception e) {
            Log.d("Erro ao atualizar empresa", e.getMessage());
        }
    }
}
