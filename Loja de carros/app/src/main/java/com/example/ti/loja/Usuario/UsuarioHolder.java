package com.example.ti.loja.Usuario;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ti.loja.R;

public class UsuarioHolder extends RecyclerView.ViewHolder{
    TextView txtNomeCelulaUsuario;

    public UsuarioHolder(View itemView){
        super(itemView);

        txtNomeCelulaUsuario = itemView.findViewById(R.id.txtNomeCelulaUsuario);

    }
}
