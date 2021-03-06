package com.example.ti.loja.Empresa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ti.loja.R;

import java.util.ArrayList;
import java.util.List;

public class EmpresaAdapter extends RecyclerView.Adapter{

    List<Empresa> listaEmpresa;
    Context context;

    public EmpresaAdapter(List<Empresa> empresa, Context context) {
        this.listaEmpresa = new ArrayList<>();
        this.listaEmpresa = empresa;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_empresa, parent, false );
        EmpresaHolder holder = new EmpresaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Empresa empresa = listaEmpresa.get(position);
        EmpresaHolder dHolder = (EmpresaHolder)holder;
        dHolder.txtNomeCelulaEmpresa.setText(empresa.getNome());
        if(empresa.getFoto() != null)
            dHolder.imageCelulaEmpresa.setImageBitmap(setImage(dHolder.imageCelulaEmpresa, empresa.getFoto()));

        dHolder.txtNomeCelulaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesEmpresaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", empresa.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaEmpresa.size();
    }

    private Bitmap setImage(ImageView image, String foto) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(foto);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        return bitmap;
    }
}
