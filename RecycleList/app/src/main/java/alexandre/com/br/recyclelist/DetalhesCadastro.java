package alexandre.com.br.recyclelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesCadastro extends AppCompatActivity {

    TextView tvNome, tvFone, tvEmail, tvEndereco;
    String nome, fone, email, endereco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_cadastro);

        tvNome = findViewById(R.id.tvDetalheNome);
        tvFone = findViewById(R.id.tvDetalheTelefone);
        tvEmail = findViewById(R.id.tvDetalheEmail);
        tvEndereco = findViewById(R.id.tvDetalheEndereco);

        Bundle b = getIntent().getExtras();
        nome = b.getString("nome");
        fone = b.getString("fone");
        email = b.getString("email");
        endereco = b.getString("endereco");

        tvNome.setText(nome);
        tvFone.setText(fone);
        tvEmail.setText(email);
        tvEndereco.setText(endereco);

    }
}
