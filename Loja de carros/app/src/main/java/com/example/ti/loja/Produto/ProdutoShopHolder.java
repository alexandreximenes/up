package com.example.ti.loja.Produto;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ti.loja.R;

public class ProdutoShopHolder extends RecyclerView.ViewHolder{
    TextView txtNomeCelulaProdutoShop;
    ImageView imageShop_lista_shop;

    public ProdutoShopHolder(View itemView){
        super(itemView);

        txtNomeCelulaProdutoShop = itemView.findViewById(R.id.txtNomeCelulaShop);
        imageShop_lista_shop = itemView.findViewById(R.id.imageShop_lista_shop);

    }
}
