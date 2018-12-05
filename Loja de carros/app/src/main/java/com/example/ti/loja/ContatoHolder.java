package com.example.ti.loja;

import android.support.v7.widget.RecyclerView;


import android.view.View;
import android.widget.TextView;


public class ContatoHolder extends RecyclerView.ViewHolder{

    TextView txtNome;

    public ContatoHolder(View itemView){
        super(itemView);

        txtNome = itemView.findViewById(R.id.txtNome);

   }

}
