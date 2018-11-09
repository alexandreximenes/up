package alexandre.com.br.recyclelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnIrParaContato;
    RecyclerView recyclerContato;

    {
        if(ContatoLista.all().isEmpty()){
            ContatoLista.generateList();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerContato = findViewById(R.id.recycleContatos);

        btnIrParaContato = findViewById(R.id.btIrParaCadastroContato);
        btnIrParaContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContatoCadastro.class));
            }
        });

        ArrayList<Contato> contatos =  ContatoLista.all();

        ContatoAdapter contatoAdapter = new ContatoAdapter(contatos, MainActivity.this);

        recyclerContato.setAdapter(contatoAdapter);

        RecyclerView.LayoutManager meuLayout = new LinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false);

        recyclerContato.setLayoutManager(meuLayout);

    }
}
