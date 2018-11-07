package alexandre.com.br.recyclelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ContatoHolder extends RecyclerView.ViewHolder {

    public TextView txtNomeCel;
    public TextView txtFoneCel;

    public ContatoHolder(@NonNull View itemView) {
        super(itemView);
        txtNomeCel = itemView.findViewById(R.id.tvNomeCel);
        txtFoneCel = itemView.findViewById(R.id.tvFoneCel);
    }




}
