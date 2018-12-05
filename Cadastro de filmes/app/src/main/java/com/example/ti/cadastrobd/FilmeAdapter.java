package com.example.ti.cadastrobd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FilmeAdapter extends RecyclerView.Adapter {

    List<Filme> listaFilmes;
    Context context;

    public FilmeAdapter(List<Filme> listaFilmes, Context context) {
        this.listaFilmes = new ArrayList<>();
        this.listaFilmes = listaFilmes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.celula, parent, false );
        FilmeHolder holder = new FilmeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final Filme filme = listaFilmes.get(position);
        FilmeHolder dHolder = (FilmeHolder)holder;
        dHolder.txtNome.setText(filme.getNome());

        dHolder.txtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", filme.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }
}
