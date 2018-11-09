package alexandre.com.br.recyclelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContatoCadastro extends AppCompatActivity {

    EditText edNome, edEmail, edTelefone, edEndereco;
    Button buttonCadastrarContato;
    TextView tvCadastro;
    ContatoLista contatoLista = new ContatoLista();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contato_cadastro);

        lerView();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            tvCadastro.setText("Editar Contato");
            index = bundle.getInt("index");
            edNome.setText(bundle.getString("nome"));
            edEmail.setText(bundle.getString("email"));
            edTelefone.setText(bundle.getString("fone"));
            edEndereco.setText(bundle.getString("endereco"));

        }

        buttonCadastrarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Contato contato = new Contato(edNome.getText().toString(), edTelefone.getText().toString(), edEmail.getText().toString(), edEndereco.getText().toString());

                if (validarDadosView()) return;

                if(index>=0){

                    Contato edt = ContatoLista.get(index);
                    edt.setNome(edNome.getText().toString());
                    edt.setEmail(edEmail.getText().toString());
                    edt.setFone(edTelefone.getText().toString());
                    edt.setEndereco(edEndereco.getText().toString());

                }else{
                    contatoLista.add(contato);
                }
                msg("Contato salvo com sucesso!");
                startActivity(new Intent(ContatoCadastro.this, MainActivity.class));

            }
        });
    }

    private boolean validarDadosView() {
        if(edNome.getText().toString().isEmpty() ||  edTelefone.getText().toString().isEmpty() || edEmail.getText().toString().isEmpty() || edEndereco.getText().toString().isEmpty()){
            msg("Preencha os campos em branco");
            return true;
        }
        return false;
    }

    private void lerView() {
        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edTelefone = findViewById(R.id.edTelefone);
        edEndereco = findViewById(R.id.edEndereco);
        buttonCadastrarContato = findViewById(R.id.buttonCadastrarContato);
        tvCadastro = findViewById(R.id.tvCadastro);

    }

    private void msg(String msg) {
        Toast.makeText(ContatoCadastro.this, msg, Toast.LENGTH_LONG).show();
    }
}
