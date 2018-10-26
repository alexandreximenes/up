package alexandre.up.com.br.gasolinaxalcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvResposta;
    Button btCalcular;
    EditText etGasolina, etAlcool;
    double valorGasolina, valorAlcool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGasolina = (EditText) findViewById(R.id.editGasolina);
        etAlcool = (EditText) findViewById(R.id.editAlcool);
        tvResposta = (TextView) findViewById(R.id.tvResultado);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorGasolina = Double.parseDouble(etGasolina.getText().toString());
                valorAlcool = Double.parseDouble(etAlcool.getText().toString());

                Double resultado = valorGasolina * 0.7;

                tvResposta.setText("Abasteça com alcool se ele tiver custando "+ NumberFormat.getCurrencyInstance().format( resultado ));
            }
        });
    }
}
