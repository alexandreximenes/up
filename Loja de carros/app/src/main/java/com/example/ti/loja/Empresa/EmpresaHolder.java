package com.example.ti.loja.Empresa;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ti.loja.R;

public class EmpresaHolder extends RecyclerView.ViewHolder{
    TextView txtNomeCelulaEmpresa;
    ImageView imageCelulaEmpresa;

    public EmpresaHolder(View itemView){
        super(itemView);

        txtNomeCelulaEmpresa = itemView.findViewById(R.id.txtNomeCelulaEmpresa);
        imageCelulaEmpresa = itemView.findViewById(R.id.imageCelulaEmpresa);

    }
}
