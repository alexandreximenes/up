package com.example.ti.cadastrobd;

import android.support.v7.widget.RecyclerView;


import android.view.View;
import android.widget.TextView;


public class FilmeHolder extends RecyclerView.ViewHolder{

    TextView txtNome;

    public FilmeHolder(View itemView){
        super(itemView);

        txtNome = itemView.findViewById(R.id.txtNome);

   }

}
