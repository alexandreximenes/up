package alexandre.com.br.recyclelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ContatoAdapter extends RecyclerView.Adapter {

    private ArrayList<Contato> listaContatos;
    private Context context;

    public ContatoAdapter(ArrayList<Contato> listaContatos, Context context) {
        this.listaContatos = listaContatos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_contato, parent, false);
        ContatoHolder holder = new ContatoHolder(view);
        return holder;
    }

    // indica qual elemento vai ser carregado
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final ContatoHolder contatoHolder = (ContatoHolder) viewHolder;

        String nome = listaContatos.get(position).getNome();
        String fone = listaContatos.get(position).getFone();

        contatoHolder.txtNomeCel.setText( nome );
        contatoHolder.txtFoneCel.setText( fone );

        contatoHolder.txtNomeCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Contato: " + position, Toast.LENGTH_SHORT).show();

                Contato c = ContatoLista.get(position);

                Bundle bundle = new Bundle();
                if(position>=0){
                    bundle.putInt("index", position);
                }
                bundle.putString("nome", c.getNome());
                bundle.putString("fone", c.getFone());
                bundle.putString("email", c.getEmail());
                bundle.putString("endereco", c.getEndereco());
                Intent i = new Intent(context, DetalhesCadastro.class);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }
}
