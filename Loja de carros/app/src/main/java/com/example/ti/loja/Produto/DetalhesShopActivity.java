package com.example.ti.loja.Produto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.dao.ProdutoDAO;

public class DetalhesShopActivity extends Activity {

    private TextView txtID, txtTituloProdutoShop, txtDescricaoProdutoShop, txtQuantidadeProdutoShop, txtPrecoProdutoShop, txtTamanhoProdutoShop, txtMarcaProdutoShop;
    private ImageView fotoProdutoShop, fundo_fotoProdutoShop;
    private Button btnVoltar;
    private Context applicationContext;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto_shop);

        applicationContext = getApplicationContext();

        txtID = findViewById(R.id.txtIdProdutoShopDetalhe);
        txtTituloProdutoShop = findViewById(R.id.txtTituloProdutoShopDetalhe);
        txtDescricaoProdutoShop = findViewById(R.id.txtDescricaoProdutoShopDetalhe);
        txtQuantidadeProdutoShop = findViewById(R.id.txtQuantidadeProdutoShopDetalhe);
        txtPrecoProdutoShop = findViewById(R.id.txtPrecoProdutoShopDetalhe);
        txtTamanhoProdutoShop = findViewById(R.id.txtTamanhoProdutoShopDetalhe);
        txtMarcaProdutoShop = findViewById(R.id.txtMarcaProdutoShopDetalhe);
        fotoProdutoShop = findViewById(R.id.imageDetalheProdutoShop);
        fundo_fotoProdutoShop = findViewById(R.id.fundo_imageDetalheProdutoShop);
        btnVoltar = findViewById(R.id.btnVoltarProdutoShopDetalhe);


        Intent detalhesIntent = getIntent();
        int id = detalhesIntent.getIntExtra("id", -1);

        if (id == -1) {
            Mensagem.show(getApplicationContext(), "Erro ao carregar detalhes do produto", Toast.LENGTH_LONG);
            startActivity(new Intent(applicationContext, ProdutosShopActivity.class));

        } else {

            ProdutoDAO dao = new ProdutoDAO(applicationContext);
            produto = dao.get(id);

            txtID.setText("ID: #" + produto.getId());
//            fotoUsuario...
//            fundo_fotoProdutoShop;
            txtTituloProdutoShop.setText("NOME: " + produto.getTitulo());
            txtDescricaoProdutoShop.setText("DESCRIÇÃO: " + produto.getDescricao());
            txtPrecoProdutoShop.setText("PREÇO: " + produto.getPreco());
            txtQuantidadeProdutoShop.setText("QUANTIDADE: " + produto.getQuantidade());
            txtMarcaProdutoShop.setText("MARCA: " + produto.getMarca());
            txtTamanhoProdutoShop.setText("TAMANHO: " + produto.getTamanho());


            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                }
            });



        }

    }
}
