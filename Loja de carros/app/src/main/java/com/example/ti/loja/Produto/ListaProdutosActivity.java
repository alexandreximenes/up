package com.example.ti.loja.Produto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ti.loja.R;
import com.example.ti.loja.dao.ProdutoDAO;

import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {

    private Button btnCadastrarProduto;
    private RecyclerView rclProduto;
    List<Produto> produtos = null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        context = ListaProdutosActivity.this;

        btnCadastrarProduto = findViewById(R.id.btnCadastrarProduto);
        rclProduto = findViewById(R.id.rclProduto);

        ProdutoDAO dao = new ProdutoDAO(context);

        produtos = dao.lista();

        ProdutoAdapter adapter = new ProdutoAdapter(produtos, context);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        rclProduto.setAdapter(adapter);
        rclProduto.setLayoutManager(layout);

        btnCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, CadastrarProduto.class));
            }
        });
    }
}
