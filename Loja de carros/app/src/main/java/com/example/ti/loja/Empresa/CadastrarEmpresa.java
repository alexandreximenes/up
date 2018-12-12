package com.example.ti.loja.Empresa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.BuildConfig;
import com.example.ti.loja.Mensagem;
import com.example.ti.loja.Produto.CadastrarProduto;
import com.example.ti.loja.R;
import com.example.ti.loja.dao.EmpresaDAO;

import java.io.File;

public class CadastrarEmpresa extends Activity {

    Button btLimpar, btSalvarEmpresa, btVoltarParaLogin, btTirarFoto;
    Integer id;
    String foto, nome, site, telefone, email, endereco;
    EditText edNome, edSite, edTelefone, edEmail, edEndereco;
    ImageView imageFoto, fundo_imageEmpresa;
    TextView tvMsgEmpresa;
    private boolean atualizar;
    private EmpresaDAO dao;
    private Empresa empresa;
    Context applicationContext;
    private String caminhoFoto;
    public static final int CAMERA_CODE = 1;

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
        fundo_imageEmpresa = findViewById(R.id.fundo_imageEmpresa);
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
                } else {
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    caminhoFoto = getExternalFilesDir(null) + File.separator + System.currentTimeMillis() + ".jpg";
                    foto = caminhoFoto;
                    Log.d("caminho foto produto", caminhoFoto);
                    File file = new File(caminhoFoto);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(file));
                    startActivityForResult(intentCamera, CAMERA_CODE);

                }

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

            //imageFoto, fundo_imageEmpresa;
            if (foto != null)
                fundo_imageEmpresa.setImageBitmap(setImage(foto));

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

    private Uri getUriFromFile(File file) {
//        if (BuildConfig.APPLICATION_ID >= 7) {
//            return FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID, file);
//        } else {
//            return Uri.fromFile(file);
//        }
        return FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID, file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == CAMERA_CODE){
            imageFoto.setImageBitmap(null);
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            fundo_imageEmpresa.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
            fundo_imageEmpresa.setScaleType(ImageView.ScaleType.FIT_XY);
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

    private Bitmap setImage(String foto) {
        // imageFoto, fundo_imageEmpresa;
        Bitmap bitmap;
        imageFoto.setImageBitmap(null);
        bitmap = BitmapFactory.decodeFile(foto);
        fundo_imageEmpresa.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
        fundo_imageEmpresa.setScaleType(ImageView.ScaleType.FIT_XY);
        return bitmap;
    }
}
