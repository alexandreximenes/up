package alexandre.com.br.recyclelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesCadastro extends AppCompatActivity {

    TextView tvNome, tvFone, tvEmail, tvEndereco;
    int index;
    String nome, fone, email, endereco;
    Button btEditar, btDeletar;
    final ContatoLista listaContatos = new ContatoLista();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_cadastro);

        tvNome = findViewById(R.id.tvDetalheNome);
        tvFone = findViewById(R.id.tvDetalheTelefone);
        tvEmail = findViewById(R.id.tvDetalheEmail);
        tvEndereco = findViewById(R.id.tvDetalheEndereco);

        Bundle b = getIntent().getExtras();
        index = b.getInt("index");
        nome = b.getString("nome");
        fone = b.getString("fone");
        email = b.getString("email");
        endereco = b.getString("endereco");

        tvNome.setText(nome);
        tvFone.setText(fone);
        tvEmail.setText(email);
        tvEndereco.setText(endereco);

        btDeletar = findViewById(R.id.btDetalheDeletar);
        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = listaContatos.remove(index);
                if(contato!=null) msg(contato.getNome() + " removido(a) com sucesso");
                startActivity(new Intent(DetalhesCadastro.this, MainActivity.class));
            }
        });
        btEditar = findViewById(R.id.btDetalheEditar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", index);
                bundle.putString("nome", tvNome.getText().toString());
                bundle.putString("email", tvEmail.getText().toString());
                bundle.putString("fone", tvFone.getText().toString());
                bundle.putString("endereco", tvEndereco.getText().toString());
                Intent i = new Intent(DetalhesCadastro.this, ContatoCadastro.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


    }

    private void msg(String msg) {
        Toast.makeText(DetalhesCadastro.this, msg , Toast.LENGTH_LONG).show();
    }
}
