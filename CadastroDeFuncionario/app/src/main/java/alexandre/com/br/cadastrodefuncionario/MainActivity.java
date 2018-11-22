package alexandre.com.br.cadastrodefuncionario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edNome, edValorHora, edHoraTrab, edCargo;
    String nome, cargo;
    float valorHora;
    int horaTrab;
    Button btCalcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.editTextNome);
        edCargo = findViewById(R.id.editCargo);
        edHoraTrab = findViewById(R.id.editTextHoraTrab);
        edValorHora = findViewById(R.id.editTextValorHoraTrab);
        btCalcular = findViewById(R.id.btCalcular);


        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edNome.getText().toString().isEmpty() ||
                        edCargo.getText().toString().isEmpty() ||
                        edHoraTrab.getText().toString().isEmpty() ||
                        edValorHora.getText().toString().isEmpty()
                    ){
                    mensagem("Preencha os campos necessarios!");
                }else {
                    nome = edNome.getText().toString();
                    cargo = edCargo.getText().toString();
                    valorHora = Float.parseFloat(edValorHora.getText().toString());
                    horaTrab = Integer.parseInt(edHoraTrab.getText().toString());
                }

                Bundle bundle = new Bundle();
                bundle.putString("nome", nome);
                bundle.putString("cargo", cargo);
                bundle.putInt("tvHora", horaTrab);
                bundle.putFloat("tvValorHora", valorHora);

                Intent intentResultado = new Intent(MainActivity.this, CadActivity.class);
                intentResultado.putExtras(bundle);
                startActivity(intentResultado);

            }
        });
    }

    private void mensagem(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }
}
