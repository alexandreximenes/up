package com.example.ti.loja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ContatoAdapter extends RecyclerView.Adapter {

    List<Contato> listaContatos;
    Context context;

    public ContatoAdapter(List<Contato> listaContatos, Context context) {
        this.listaContatos = new ArrayList<>();
        this.listaContatos = listaContatos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.celula, parent, false );
        ContatoHolder holder = new ContatoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final Contato contato = listaContatos.get(position);
        ContatoHolder dHolder = (ContatoHolder)holder;
        dHolder.txtNome.setText(contato.getNome());

        dHolder.txtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", contato.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }
}
