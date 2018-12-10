package com.example.ti.loja.Produto;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ti.loja.R;

public class ProdutoHolder extends RecyclerView.ViewHolder{
    TextView txtNomeCelulaProduto;

    public ProdutoHolder(View itemView){
        super(itemView);

        txtNomeCelulaProduto = itemView.findViewById(R.id.txtNomeCelulaProduto);

    }
}
