package com.example.ti.loja.Produto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.ti.loja.R;
import com.example.ti.loja.dao.ProdutoDAO;

import java.io.File;

public class CadastrarProduto extends Activity {

    public static final int CAMERA_CODE = 1;
    Button btLimparProduto, btSalvarProduto, btVoltarProduto, btTirarFotoProduto;
    Integer id, quantidade;
    Double preco;
    String foto, titulo, descricao, marca, tamanho;
    EditText edTituloProduto, edDescricaoProduto, edQuantidadeProduto, edPrecoProduto, edMarcaProduto, edTamanhoProduto;
    ImageView imageFoto, fundo_imageFoto;
    TextView tvMsgProduto;
    private boolean atualizar;
    private ProdutoDAO dao;
    private Produto produto;
    Context applicationContext;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        applicationContext = getApplicationContext();

        edTituloProduto = findViewById(R.id.edTituloProduto);
        edDescricaoProduto = findViewById(R.id.edDescricaoProduto);
        edQuantidadeProduto = findViewById(R.id.edQuantidadeProduto);
        edPrecoProduto = findViewById(R.id.edPrecoProduto);
        edMarcaProduto = findViewById(R.id.edMarcaProduto);
        edTamanhoProduto = findViewById(R.id.edTamanhoProduto);
        imageFoto = findViewById(R.id.imageProduto);
        fundo_imageFoto = findViewById(R.id.fundo_imageProduto);
        tvMsgProduto = findViewById(R.id.tvMsgProduto);

        btLimparProduto = findViewById(R.id.btLimparProduto);
        btTirarFotoProduto = findViewById(R.id.btnTirarFotoProduto);
        btSalvarProduto = findViewById(R.id.btSalvarProduto);
        btVoltarProduto = findViewById(R.id.btVoltarProduto);

        btTirarFotoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CadastrarProduto.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                } else {
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    caminhoFoto = getExternalFilesDir(null) + File.separator + System.currentTimeMillis() + ".jpg";
                    Log.d("caminho foto produto", caminhoFoto);
                    File file = new File(caminhoFoto);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(file));
                    startActivityForResult(intentCamera, CAMERA_CODE);

                }

            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        produto = new Produto();
        atualizar = false;

        if (bundle != null) {
            atualizar = true;
            id = bundle.getInt("id");
            foto = bundle.getString("foto");
            titulo = bundle.getString("titulo");
            descricao = bundle.getString("descricao");
            preco = bundle.getDouble("preco");
            quantidade = bundle.getInt("quantidade");
            tamanho = bundle.getString("tamanho");
            marca = bundle.getString("marca");

            //imagem imageFoto
            edTituloProduto.setText(titulo);
            edPrecoProduto.setText(preco.toString());
            edDescricaoProduto.setText(descricao);
            edQuantidadeProduto.setText(quantidade.toString());
            edMarcaProduto.setText(marca);
            edPrecoProduto.setText(preco.toString());
            edTamanhoProduto.setText(tamanho);

        }

        btSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //foto = imageFoto
                titulo = edTituloProduto.getText().toString();
                descricao = edDescricaoProduto.getText().toString();
                quantidade = edQuantidadeProduto.getText().toString().isEmpty() ? 0 : Integer.parseInt(edQuantidadeProduto.getText().toString());
                marca = edMarcaProduto.getText().toString();
                tamanho = edTamanhoProduto.getText().toString();
                preco = edPrecoProduto.getText().toString().isEmpty() ? 0 : Double.parseDouble(edPrecoProduto.getText().toString());

                if (validacaoDeCampos()) return;
                tvMsgProduto.setText("");
                setProduto();

                dao = new ProdutoDAO(applicationContext);

                if (atualizar) {
                    atualizarProduto(produto);
                } else {
                    cadastrarProduto(produto);
                }


                startActivity(new Intent(applicationContext, ListaProdutosActivity.class));

            }
        });
        btVoltarProduto.setOnClickListener(view -> finish());
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
            fundo_imageFoto.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
            fundo_imageFoto.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//            imageFoto.setImageBitmap(bitmap);

        }
    }

    private boolean validacaoDeCampos(){
        if (titulo.isEmpty() || descricao.isEmpty() || quantidade <= 0 || preco <= 0) {
            Mensagem.show(getApplicationContext(),"Preencha os campos obrigatórios", Toast.LENGTH_LONG);
            tvMsgProduto.setText("Titulo, descricao, quantidade e preço são obrigatórios");
            if(preco <= 0){
                edPrecoProduto.requestFocus();
            }
            if(quantidade <= 0){
                edQuantidadeProduto.requestFocus();
            }
            if(descricao.isEmpty()){
                edDescricaoProduto.requestFocus();
            }
            if(titulo.isEmpty()){
                edTituloProduto.requestFocus();
            }

            return true;
        }
        return false;
    }

    private void setProduto() {

        produto.setId(id);
        produto.setTitulo(titulo);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setMarca(marca);
        produto.setTamanho(tamanho);

    }

    private void atualizarProduto(Produto produto) {
        try {

            if (dao.atualizar(produto)) {
                Mensagem.show(getApplicationContext(),"Produto atualizada com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Não foi possivel atualizar produto", Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            Log.d("Erro ao atualizar produto", e.getMessage());
        }
    }

    private void cadastrarProduto(Produto produto) {
        try {

            if (dao.cadastrar(produto)) {
                Mensagem.show(getApplicationContext(),"Produto(a) cadastrado(a) com sucesso!", Toast.LENGTH_LONG);
            } else {
                Mensagem.show(getApplicationContext(),"Produto(a) atualizado(a) com sucesso!", Toast.LENGTH_LONG);
            }

        } catch (Exception e) {
            Log.d("Erro ao atualizar produto", e.getMessage());
        }
    }
}
