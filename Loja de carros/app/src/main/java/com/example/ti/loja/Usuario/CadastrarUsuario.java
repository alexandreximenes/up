package com.example.ti.loja.Usuario;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.ti.loja.dao.UsuarioDAO;

import java.io.File;

public class CadastrarUsuario extends Activity {

    Button btLimpar, btSalvarUsuario, btVoltarParaLogin, btTirarFoto;
    Integer id;
    String foto, nome, email, senha;
    EditText edNome, edEmail, edSenha;
    ImageView imageFoto, fundo_imageUsuario;
    TextView tvMsgUsuario;
    private boolean atualizar;
    private UsuarioDAO dao;
    private Usuario usuario;
    Context applicationContext;
    public static final int CAMERA_CODE = 1;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        applicationContext = getApplicationContext();

        edNome = findViewById(R.id.edNomeUsuario);
        edEmail = findViewById(R.id.edEmailUsuario);
        edSenha = findViewById(R.id.edSenhaUsuario);
        imageFoto = findViewById(R.id.imageUsuario);
        fundo_imageUsuario = findViewById(R.id.fundo_imageUsuario);
        tvMsgUsuario = findViewById(R.id.tvMsgUsuario);


        btLimpar = findViewById(R.id.btLimparUsuario);
        btTirarFoto = findViewById(R.id.btnTirarFotoUsuario);
        btSalvarUsuario = findViewById(R.id.btSalvarUsuario);
        btVoltarParaLogin = findViewById(R.id.btVoltarUsuario);

        btTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CadastrarUsuario.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                } else {
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    caminhoFoto = getExternalFilesDir(null) + File.separator + System.currentTimeMillis() + ".jpg";
                    Log.d("caminho foto usuario", caminhoFoto);
                    File file = new File(caminhoFoto);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(file));
                    startActivityForResult(intentCamera, CAMERA_CODE);

                }

            }
        });


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

            //imageFoto, fundo_imageUsuario;
            if (foto != null)
                fundo_imageUsuario.setImageBitmap(setImage(foto));

            edNome.setText(nome);
            edEmail.setText(email);
            edSenha.setText(senha);

        }

        btSalvarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //foto = imageFoto
                nome = edNome.getText().toString();
                email = edEmail.getText().toString();
                senha = edSenha.getText().toString();

                if (validacaoDeCampos()) return;
                tvMsgUsuario.setText("");
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
            fundo_imageUsuario.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
            fundo_imageUsuario.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    private boolean validacaoDeCampos(){
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Mensagem.show(getApplicationContext(),"Preencha todos os campos", Toast.LENGTH_LONG);

            if(senha.isEmpty()){
                edSenha.requestFocus();
            }
            if(email.isEmpty()){
                edEmail.requestFocus();
            }
            if(nome.isEmpty()){
                edNome.requestFocus();
            }


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
                Mensagem.show(getApplicationContext(),"Usuario(a) cadastrado(a) com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Usuario(a) atualizado(a) com sucesso!", Toast.LENGTH_LONG);
            }

        } catch (Exception e) {
            Log.d("Erro ao atualizar usuario", e.getMessage());
        }
    }

    private Bitmap setImage(String foto) {
        //imageFoto, fundo_imageUsuario;
        Bitmap bitmap;
        imageFoto.setImageBitmap(null);
        bitmap = BitmapFactory.decodeFile(foto);
        fundo_imageUsuario.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
        fundo_imageUsuario.setScaleType(ImageView.ScaleType.FIT_XY);
        return bitmap;
    }


}


