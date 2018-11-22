package com.example.ti.cadastrobd;

import android.support.v7.widget.RecyclerView;


import android.view.View;
import android.widget.TextView;


public class DisciplinasHolder  extends RecyclerView.ViewHolder{

    TextView txtNome;

    public DisciplinasHolder(View itemView){
        super(itemView);

        txtNome = itemView.findViewById(R.id.txtNome);

   }

}
