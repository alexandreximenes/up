package alexandre.com.br.recyclelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnContato;
    RecyclerView recyclerContato;

    {
        if(ContatoLista.getListaContatos().isEmpty()){
            ContatoLista.gerarLista();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerContato = findViewById(R.id.recycleContatos);

        btnContato = findViewById(R.id.btAddContato);

        ArrayList<Contato> listaContatos =  ContatoLista.getListaContatos();

        ContatoAdapter contatoAdapter = new ContatoAdapter(listaContatos, MainActivity.this);

        recyclerContato.setAdapter(contatoAdapter);

        RecyclerView.LayoutManager meuLayout = new LinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false);

        recyclerContato.setLayoutManager(meuLayout);

    }
}
