package com.example.ti.cadastrobd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FilmeAdapter extends RecyclerView.Adapter {

    ArrayList<Filme> listaFilmes;
    Context context;

    public FilmeAdapter(ArrayList<Filme> listaFilmes, Context context) {
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

        FilmeHolder dHolder = (FilmeHolder)holder;
        dHolder.txtNome.setText(listaFilmes.get(position).getNome());

        dHolder.txtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesActivity.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
//        return listaFilmes.size()>0 ? listaFilmes.size() : 0;
    }
}
