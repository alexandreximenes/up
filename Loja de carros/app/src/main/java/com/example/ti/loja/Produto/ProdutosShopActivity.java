package com.example.ti.loja.Produto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.Usuario.LoginActivity;
import com.example.ti.loja.dao.ProdutoDAO;

import java.util.List;

public class ProdutosShopActivity extends Activity {

    private RecyclerView rclShop;
    List<Produto> produtos = null;
    private Context context;
    ImageButton imageButtonSearch, btnAcessarAdmin;
    EditText input_search;
    ProdutoShopAdapter adapter;
    RecyclerView.LayoutManager layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos_shop);

        context = ProdutosShopActivity.this;

        rclShop = findViewById(R.id.rclShop);
        imageButtonSearch = findViewById(R.id.imageButtonSearch);
        input_search = findViewById(R.id.input_search);
        btnAcessarAdmin = findViewById(R.id.btnAcessarAdmin);


        ProdutoDAO dao = new ProdutoDAO(context);

        if(input_search.getText().toString().isEmpty()){
            produtos = dao.lista();
            enviarProdutosParaRecycle();
        }

        btnAcessarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, LoginActivity.class));
            }
        });

        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_search.getText().toString().isEmpty()){
                    produtos = dao.lista();
                    enviarProdutosParaRecycle();
                }else{
                    getProdutos(dao);
                }
            }
        });

    }

    private void enviarProdutosParaRecycle() {
        adapter = new ProdutoShopAdapter(produtos, context);
        layout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rclShop.setAdapter(adapter);
        rclShop.setLayoutManager(layout);
    }

    private void getProdutos(ProdutoDAO dao) {
            produtos = dao.listaPorNome(input_search.getText().toString());

            if(produtos.isEmpty()){
                Mensagem.show(getApplicationContext(), "Não foi encontrado produtos para esta pesquisa", Toast.LENGTH_LONG);
                return;
            }else{
                enviarProdutosParaRecycle();
            }
    }
}
