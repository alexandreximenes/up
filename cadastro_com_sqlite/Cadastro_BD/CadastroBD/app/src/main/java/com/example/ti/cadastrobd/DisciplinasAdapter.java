package com.example.ti.cadastrobd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class DisciplinasAdapter extends RecyclerView.Adapter {

    ArrayList<Disciplina> listaDisciplinas;
    Context context;

    public DisciplinasAdapter(ArrayList<Disciplina> listaDisciplinas, Context context) {
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.celula, parent, false );
        DisciplinasHolder holder = new DisciplinasHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        DisciplinasHolder dHolder = (DisciplinasHolder)holder;
        dHolder.txtNome.setText(listaDisciplinas.get(position).getNome());

        dHolder.txtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesActivity.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }
}
