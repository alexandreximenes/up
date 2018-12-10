package com.example.ti.loja.Produto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ti.loja.R;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter{

    List<Produto> listaProduto;
    Context context;

    public ProdutoAdapter(List<Produto> produto, Context context) {
        this.listaProduto = new ArrayList<>();
        this.listaProduto = produto;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_produto, parent, false );
        ProdutoHolder holder = new ProdutoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Produto produto = listaProduto.get(position);
        ProdutoHolder dHolder = (ProdutoHolder)holder;
        dHolder.txtNomeCelulaProduto.setText(produto.getTitulo());

        dHolder.txtNomeCelulaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesProdutoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", produto.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProduto.size();
    }
}
