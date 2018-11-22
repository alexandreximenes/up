package alexandre.com.br.cadastrodefuncionario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CadActivity extends AppCompatActivity {

    TextView tvNome, tvCargo, tvAbono, tvHoras, tvValorHora, tvSalBruto, tvSalLiq;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        tvNome = findViewById(R.id.tvNomeFuncionario);
        tvCargo = findViewById(R.id.tvCargo);
        tvAbono = findViewById(R.id.tvAbono);
        tvHoras = findViewById(R.id.tvHoraTrabalhada);
        tvValorHora = findViewById(R.id.tvValorHoraTrab);
        tvSalBruto = findViewById(R.id.tvSalarioBruto);
        tvSalLiq = findViewById(R.id.tvSalarioLiquido);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String nome = bundle.getString("nome");
        String cargo = bundle.getString("cargo");
        int horasTrabalhadas = bundle.getInt("tvHora");
        float valorHora = bundle.getFloat("tvValorHora");

        Toast.makeText(CadActivity.this, nome, Toast.LENGTH_LONG).show();

        Funcionario funcionario = new Funcionario(nome, cargo, horasTrabalhadas, valorHora);

        tvNome.setText(nome);
        tvCargo.setText(cargo);
        tvHoras.setText(String.valueOf(horasTrabalhadas));
        tvValorHora.setText(String.valueOf(valorHora));
        tvAbono.setText(String.valueOf(funcionario.getAbono()));
        tvSalBruto.setText(String.valueOf(funcionario.getSalBruto()));
        tvSalLiq.setText(String.valueOf(funcionario.getSalLiq()));

        btVoltar = findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
