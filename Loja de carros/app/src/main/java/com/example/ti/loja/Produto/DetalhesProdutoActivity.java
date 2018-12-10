package com.example.ti.loja.Produto;

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
import com.example.ti.loja.dao.ProdutoDAO;

public class DetalhesProdutoActivity extends Activity {

    private TextView txtID, txtTituloProduto, txtDescricaoProduto, txtQuantidadeProduto, txtPrecoProduto, txtTamanhoProduto, txtMarcaProduto;
    private ImageView fotoProduto;
    private Button btnExcluir, btnEditar;
    private Context applicationContext;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        applicationContext = getApplicationContext();

        txtID = findViewById(R.id.txtIdProdutoDetalhe);
        txtTituloProduto = findViewById(R.id.txtTituloProdutoDetalhe);
        txtDescricaoProduto = findViewById(R.id.txtDescricaoProdutoDetalhe);
        txtQuantidadeProduto = findViewById(R.id.txtQuantidadeProdutoDetalhe);
        txtPrecoProduto = findViewById(R.id.txtPrecoProdutoDetalhe);
        txtTamanhoProduto = findViewById(R.id.txtTamanhoProdutoDetalhe);
        txtMarcaProduto = findViewById(R.id.txtMarcaProdutoDetalhe);
        fotoProduto = findViewById(R.id.imageDetalheProduto);
        btnExcluir = findViewById(R.id.btnExcluirProdutoDetalhe);
        btnEditar = findViewById(R.id.btnEditarProdutoDetalhe);


        Intent detalhesIntent = getIntent();
        int id = detalhesIntent.getIntExtra("id", -1);

        if (id == -1) {
            Mensagem.show(getApplicationContext(), "Erro ao carregar detalhes do produto", Toast.LENGTH_LONG);
            startActivity(new Intent(applicationContext, ListaProdutosActivity.class));

        } else {

            ProdutoDAO dao = new ProdutoDAO(applicationContext);
            produto = dao.get(id);

            txtID.setText("ID: #" + produto.getId());
//            fotoUsuario...
            txtTituloProduto.setText("NOME: " + produto.getTitulo());
            txtDescricaoProduto.setText("DESCRIÇÃO: " + produto.getDescricao());
            txtPrecoProduto.setText("PREÇO: " + produto.getPreco());
            txtQuantidadeProduto.setText("QUANTIDADE: " + produto.getQuantidade());
            txtMarcaProduto.setText("MARCA: " + produto.getMarca());
            txtTamanhoProduto.setText("TAMANHO: " + produto.getTamanho());


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent vaiParaCadastroActivity = new Intent(getApplicationContext(), CadastrarProduto.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("id", produto.getId());
                    bundle.putString("foto", produto.getFoto());
                    bundle.putString("titulo", produto.getTitulo());
                    bundle.putString("descricao", produto.getDescricao());
                    bundle.putDouble("preco", produto.getPreco());
                    bundle.putInt("quantidade", produto.getQuantidade());
                    bundle.putString("tamanho", produto.getTamanho());
                    bundle.putString("marca", produto.getMarca());

                    vaiParaCadastroActivity.putExtras(bundle);
                    startActivity(vaiParaCadastroActivity);
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        ProdutoDAO dao = new ProdutoDAO(applicationContext);

                        if (dao.excluir(produto.getId())) {
                            Mensagem.show(applicationContext, "Produto excluida com sucesso!", Toast.LENGTH_LONG);
                        } else {
                            Mensagem.show(applicationContext, "Erro ao excluir produto", Toast.LENGTH_LONG);
                        }
                        startActivity(new Intent(applicationContext, ListaProdutosActivity.class));

                    } catch (Exception e) {
                        Mensagem.show(applicationContext, e.getMessage().toString(), Toast.LENGTH_LONG);
                    }
                }
            });


        }

    }
}
