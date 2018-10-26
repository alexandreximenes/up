package com.example.up.converter_km_para_ml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txtResposta;
    Button btnConverter;
    EditText edtKm;

    float milhas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResposta  = findViewById(R.id.txtResposta);
        btnConverter = findViewById(R.id.btnConverter);
        edtKm        = findViewById(R.id.edtKm);


        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                milhas = Float.parseFloat(edtKm.getText().toString()) * 1.6f;

                String convertido = String.format(Locale.FRANCE, "%.2f", milhas);

                txtResposta.setText("Distância em Milhas: " + convertido + " ML");

                edtKm.setText("");

            }
    });


    }
}
