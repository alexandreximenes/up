package com.example.ti.loja.Usuario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ti.loja.DetalhesUsuarioActivity;
import com.example.ti.loja.R;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter{

    List<Usuario> listaUsuarios;
    Context context;

    public UsuarioAdapter(List<Usuario> usuarios, Context context) {
        this.listaUsuarios = new ArrayList<>();
        this.listaUsuarios = usuarios;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_usuario, parent, false );
        UsuarioHolder holder = new UsuarioHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Usuario usuario = listaUsuarios.get(position);
        UsuarioHolder dHolder = (UsuarioHolder)holder;
        dHolder.txtNomeCelulaUsuario.setText(usuario.getNome());

        dHolder.txtNomeCelulaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesUsuarioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", usuario.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }
}
